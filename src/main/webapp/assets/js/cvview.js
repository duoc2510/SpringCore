(function () {
    let printable;
    let themes = [
        "theme-default",
        "theme-modern",
        "theme-lavender",
        "theme-deco",
    ];
    let numOfSections = 0;
    let defaultCv;

    //Retrieve CV and theme saved to local storage
    let savedCv;
    let usedTheme;
    try {
        savedCv = JSON.parse(localStorage.getItem("CV"));
        usedTheme = JSON.parse(localStorage.getItem("theme"));
    } catch (error) {
        console.log(error);
    }

    //Display CV on page
    function setContent(sections) {
        let loadedCv = convertSavedData(sections);
        printable.html(loadedCv);
        createNewElementButton();
        makeSortable();
        console.log("Nội dung đã được cập nhật");
    }

    //Traversing JSON data, returns CV data as string of formatted HTML in an array
    function convertSavedData(sections) {
        return sections.map(section => {
            let sectionContent = JSON.parse(section.sectionContent);  // Phân tích chuỗi JSON thành đối tượng
            switch (section.sectionType) {
                case "info":
                    return infoToHtml(sectionContent);
                case "listing":
                    return listingToHtml(sectionContent);
                case "single-block":
                    return singleBlockToHtml(sectionContent);
                case "three-column":
                    return threeColToHtml(sectionContent);
                default:
                    console.log("Loại không xác định:", section.sectionType);
                    return "";
            }
        }).join("");
    }

    //Saving CV information to an array of Objects
    function saveCvToArray() {
        return [...document.querySelectorAll("#printable section")].map(
                function (section) {
                    let sectionId = section.id;
                    //Converts Info sections JSON data
                    if (section.classList.contains("info")) {
                        return infoToObject(sectionId);
                    }
                    //Converts three-column sections into JSON data
                    else if (section.classList.contains("three-column")) {
                        return threeColToObject(sectionId);
                    }
                    // Converts listing sections into JSON data
                    else if (section.classList.contains("listing")) {
                        return listingToObject(sectionId);
                    }
                    //Converts single block sections into JSON data
                    else if (section.classList.contains("single-block")) {
                        return singleBlockToObject(sectionId);
                    }
                }
        );
    }

    //Toggles elements that should not be seen in the PDF
    function toggleUnprinted() {
        $(".add-element").each(function () {
            if ($(this).hasClass("d-flex")) {
                $(this).removeClass("d-flex").addClass("invisibleFlex");
            } else if ($(this).hasClass("invisibleFlex")) {
                $(this).removeClass("invisibleFlex").addClass("d-flex");
            }
            $(this).toggle();
        });
    }

    //GENERATE HTML FOR ENTIRE SECTIONS

    //Arguments are objects from JSON data, whether default CV or CV saved in local storage
    //Default arguments are used when a new section is created

    //Generate HTML for info sections, returns a string of HTML
    function infoToHtml(section) {
        let dataTable1 = convertTableToHtml(section.table1);
        let dataTable2 = convertTableToHtml(section.table2);
        numOfSections++;
        return `<section class="section info deletable sortable break-after" id="section${numOfSections}">
            <h2 contenteditable="true" class="text-center info-name">${section.name}</h2>
            <div class="row">${dataTable1 + dataTable2}</div>
        </section>`;
    }

    //Generate HTML for tables in info sections, returns a string of HTML
    function convertTableToHtml(table) {
        let dataTable = "";
        for (let j = 0; j < table.label.length; j++) {
            let newRow = createInfoItem(table.label[j], table.content[j]);
            //Table has only 1 row, open and close div tag
            if (table.label.length === 1) {
                dataTable += `<div class="col-12 col-sm-6 extendable info-table ${table.class} sortable-list">${newRow}</div>`;
            }
            //First row of the table, open div tag, no closing div tag
            else if (j === 0) {
                dataTable += `<div class="col-12 col-sm-6 extendable info-table ${table.class} sortable-list">${newRow}`;
            }
            //Last row of the table, no open div tag, close div tag
            else if (j === table.label.length - 1) {
                dataTable += `${newRow}</div>`;
            }
            //Every other row
            else {
                dataTable += newRow;
            }
        }
        return dataTable;
    }

    //Generate HTML for single block sections
    function singleBlockToHtml(section) {
        let htmlList = section.list.map(item => createSingleBlockItem(item)).join('');
        numOfSections++;
        return `<section class="break-after section single-block deletable extendable sortable-list sortable" id="section${numOfSections}">
            <h3 contenteditable="true" class="section-heading single-block-title">${section.title}</h3>${htmlList}
        </section>`;
    }

    //Generate HTML for listing sections
    function listingToHtml(section) {
        let htmlList = section.list.map(item => createListingItem(item)).join('');
        numOfSections++;
        return `<section class="section break-after listing deletable extendable sortable-list sortable" id="section${numOfSections}">
            <h3 contenteditable="true" class="listing-title section-heading">${section.title}</h3>${htmlList}
        </section>`;
    }

    //Generate HTML for three column sections
    function threeColToHtml(section) {
        let htmlList = section.list.map(item => createThreeColumnItem(item)).join('');
        numOfSections++;
        return `<section class="section three-column deletable sortable" id="section${numOfSections}">
            <h3 contenteditable="true" class="section-heading three-col-title">${section.title}</h3>
            <div class="row three-column-list d-flex align-content-start flex-wrap extendable pl-2 pr-2 sortable-list">${htmlList}</div>
        </section>`;
    }

    function saveCvToServer() {
        const jobSeekerId = document.getElementById("jobSeekerId").value;
        const currentCv = saveCvToArray();

        const sections = currentCv.map(section => ({
                sectionType: section.type,
                sectionContent: JSON.stringify(section)
            }));

        fetch("/cv/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                jobSeekerId: jobSeekerId,
                sections: sections
            }),
        })
                .then(response => response.text())
                .then(data => {
                    console.log("Phản hồi:", data);
                    $("#save-alert").show("blind", 100);
                    setTimeout(() => $("#save-alert").hide("blind", 100), 8000);
                })
                .catch(error => console.error("Lỗi khi lưu CV:", error));
    }

    /* Generate HTML for elements within sections
     Default parameters are used when a new element is created*/

    //Create HTML for item in Info section
    function createInfoItem(label = "Label", content = "Information") {
        return `<div class="row deletable sortable">
        <div class="col-12 col-md-4 text-left info-label" contenteditable="true">${label}</div>
        <div contenteditable="true" class="col-12 col-md-8 info-content">${content}</div>
    </div>`;
    }

    //Create HTML for item in Listing section
    function createListingItem(
            listing = {
            date: "Date",
                    location: "Location",
                    position: "Position",
                    description: "Description",
            }
    ) {
        return `<div class="row listing-row deletable sortable">
    <div class="col-md-2 listing-date-col">
        <h5 contenteditable="true" class="listing-date">${listing.date}</h5>
    </div>
    <div class="col-md-2 listing-location-col">
        <h5 contenteditable="true" class="listing-location">${listing.location}</h5>
    </div>
    <div class="col-md-8 listing-content-col">
        <h5 contenteditable="true" class="listing-position">
        ${listing.position}
        </h5>
        <div contenteditable="true" class="listing-description">
        ${listing.description}
        </div>
    </div>
</div>`;
    }

    //Create HTML for item in Single Block section
    function createSingleBlockItem(textContent = "Description") {
        return `<div class="row deletable sortable"><div class="col"><div contenteditable="true" class="single-block-content">${textContent}</div></div></div>
</div>`;
    }

    //Create HTML for item in Three Column section
    function createThreeColumnItem(item = "New Item") {
        return `<div class="col-12 col-md-4 sortable deletable"><ul><li contenteditable="true" class="three-col-item">${item}</li></ul></div>`;
    }

    /* Adding a new section to CV */

    //Toggle buttons to add new section
    function toggleSectionBtns() {
        $("#close-section").toggle();
        $("#add-section-btn").toggle();
        $("#new-section-buttons").toggle("blind", "swing", 300);
    }

    //Adds a new section to the cv and ensures it is sortable and has buttons to add a new element
    function addSection(type) {
        $("#printable").append(type);
        toggleSectionBtns();
        createNewElementButton();
        makeSortable();
    }

    /* Creates objects for each type of section from HTML */

    //Fetches information in info HTML section and generates an Object
    function infoToObject(id) {
        let infoObj = {};
        infoObj.type = "info";
        infoObj.name = $("#" + id + " .info-name").html();
        infoObj.table1 = {
            class: "info-table1",
            label: [],
            content: [],
        };
        infoObj.table2 = {
            class: "info-table2",
            label: [],
            content: [],
        };
        $("#" + id + " .info-table1 .info-label").each(function () {
            infoObj.table1.label.push($(this).html());
        });
        $("#" + id + " .info-table1 .info-content").each(function () {
            infoObj.table1.content.push($(this).html());
        });
        $("#" + id + " .info-table2 .info-label").each(function () {
            infoObj.table2.label.push($(this).html());
        });
        $("#" + id + " .info-table2 .info-content").each(function () {
            infoObj.table2.content.push($(this).html());
        });
        return infoObj;
    }

    //Fetches information in 3 Column HTML section and generates an object
    function threeColToObject(id) {
        let threeColObj = {};
        threeColObj.type = "three-column";
        threeColObj.title = $("#" + id + " .three-col-title").html();
        threeColObj.list = [];
        $("#" + id + " .three-col-item").each(function () {
            threeColObj.list.push($(this).html());
        });
        return threeColObj;
    }

    //Fetches information in Listing HTML section and generates an object
    function listingToObject(id) {
        listingObj = {};
        listingObj.type = "listing";
        listingObj.title = $("#" + id + " .listing-title").html();
        listingObj.list = [];
        $("#" + id + " .listing-row").each(function () {
            let listingRow = {};
            listingRow.date = $(this).find(".listing-date").html();
            listingRow.location = $(this).find(".listing-location").html();
            listingRow.position = $(this).find(".listing-position").html();
            listingRow.description = $(this)
                    .find(".listing-description")
                    .html();

            listingObj.list.push(listingRow);
        });
        return listingObj;
    }

    //Fetches information in Single Block HTML section and generates an object
    function singleBlockToObject(id) {
        singleBlockObj = {};
        singleBlockObj.type = "single-block";
        singleBlockObj.title = $("#" + id + " .single-block-title").html();
        singleBlockObj.list = [];
        $("#" + id + " .single-block-content").each(function () {
            singleBlockObj.list.push($(this).html());
        });
        return singleBlockObj;
    }

    /* Generating buttons to add and delete elements pn CV */

    //Display "delete" button on mouse enter
    $(document).on("mouseenter", ".deletable", function () {
        //Delete button is only created if one doesn't already exist
        if (!$(this).find("deletebtn").length) {
            $(this).append(
                    `<button class="deletebtn" aria-label="Delete element"><i class='fas fa-times-circle'></i></button>`
                    );
            $(this).addClass("deletable-hover");
        }
    });
    //Remove button and deletable class on mouse leave
    $(document).on("mouseleave", ".deletable", function () {
        $(this).find(".deletebtn").remove();
        $(this).removeClass("deletable-hover");
    });

    //Remove element or section on click of delete button
    $(document).on("click", ".deletebtn", function () {
        $(this).closest(".deletable").remove();
    });

    //Button to add a new element is generated in every section
    function createNewElementButton() {
        $(".extendable").each(function () {
            if (!$(this).find(".add-element").length) {
                if ($(this).parents().hasClass("three-column")) {
                    //Three column section "add element" button behaves slightly differently due to bootstrap column
                    $(this).append(
                            `<button class="add-element btn-block d-flex justify-content-center align-items-center col-md-4" aria-label="Add element"><i class="fas fa-plus-circle"></i></button>`
                            );
                } else {
                    $(this).append(
                            `<button class="add-element btn-block text-center row"  aria-label="Add element"><i class="fas fa-plus-circle"></i></button>`
                            );
                }
            }
        });
    }

    //Add new element
    $(document).on("click", ".add-element", addItem);

    function addItem() {
        var newItem = "";
        if ($(this).parent().hasClass("listing")) {
            newItem = createListingItem();
        } else if ($(this).parent().hasClass("single-block")) {
            newItem = createSingleBlockItem();
        } else if ($(this).parent().hasClass("three-column-list")) {
            newItem = createThreeColumnItem();
        } else if ($(this).parent().hasClass("info-table")) {
            newItem = createInfoItem();
        }
        //The new element is added before the button
        $(this).before(newItem);
    }

    /* Make sections and elements sortable with JQuery UI */

    //Add button to sort elements on mouse event
    $(document).on("mouseenter", ".sortable", function () {
        //Add sortable handle only if is not already appended to the element
        if (!$(this).find("sortable-btn").length) {
            $(this).append(
                    `<button class="sortable-btn"><i class="fas fa-bars"></i></button>`
                    );
        }
    });

    //Remove sortable handle on mouse leave
    $(document).on("mouseleave", ".sortable", function () {
        $(this).find(".sortable-btn").remove();
    });

    //JQuery UI sortable function with options
    function makeSortable() {
        $(".sortable-list").sortable({
            items: ">.sortable",
            handle: ">.sortable-btn",
            cancel: "",
            placeholder: "sort-placeholder",
            cursor: "grabbing",
            helper: "clone",
            opacity: 0.8,
            revert: 100,
            scrollSensitivity: 60,
            scrollSpeed: 10,
            tolerance: "touch",
            html2canvas: {windowWidth: 600},
        });
    }

    /* Convert to PDF */

    //HTML2PDF settings
    let printOptions = {
        filename: "cv.pdf",
        pagebreak: {mode: "avoid-all"},
        image: {type: "jpeg", quality: 0.98},
        enableLinks: true,
    };

    //Transform urls in text to <a> tags with anchorme.js
    function makeAnchors(id) {
        let content = document.getElementById(id).innerHTML;
        document.getElementById(id).innerHTML = anchorme({
            input: content,
        });
    }

    //Print CV to a pdf file with html2pdf.js
    function toPDF() {
        toggleUnprinted();
        makeAnchors("printable");
        html2pdf()
                .set(printOptions)
                .from(printable[0])
                .save()
                .then(toggleUnprinted)
                .then(makeSortable);
    }

    $("#download-btn").click(toPDF);

    /* Changing Themes */

    //Update active theme in navigation
    function changeActiveNav(nav) {
        $(".nav-link .sr-only").remove();
        $(".active").removeClass("active");
        nav.addClass("active");
        nav.append(`<span class="sr-only">(current)</span>`);
    }

    //Update CV themes and changes color of header
    function changeTheme(theme) {
        let themeClasses = themes.join(" ");
        $("#printable").removeClass(themeClasses).addClass(theme);
        $(".custom-header").removeClass(themeClasses).addClass(theme);
        usedTheme = theme;
    }

    $(document).ready(function () {
        $("#save-alert").hide();
        $("#new-section-buttons").hide();
        $("#close-section").hide();
        printable = $("#printable");

        //Nav links listeners
        $("#theme-default-link").click(function () {
            changeTheme("theme-default");
        });
        $("#theme-modern-link").click(function () {
            changeTheme("theme-modern");
        });
        $("#theme-lavender-link").click(function () {
            changeTheme("theme-lavender");
        });
        $("#theme-deco-link").click(function () {
            changeTheme("theme-deco");
        });

        //New sections added on click
        $("#newsingleblock-btn").click(function () {
            addSection(singleBlockToHtml());
        });
        $("#newlisting-btn").click(function () {
            addSection(listingToHtml());
        });
        $("#new3col-btn").click(function () {
            addSection(threeColToHtml());
        });
        $("#newinfo-btn").click(function () {
            addSection(infoToHtml());
        });

        //When Button to add section is clicked, section type selection appears
        $("#add-section-btn").click(toggleSectionBtns);

        //Closing section type selection
        $("#close-section").click(function () {
            $("#new-section-buttons").toggle(
                    "blind",
                    "swing",
                    300,
                    function () {
                        $("#close-section").toggle();
                        $("#add-section-btn").toggle();
                    }
            );
        });
//        $("#save-btn").click(function () {
//            const jobSeekerId = document.getElementById("jobSeekerId").value;
//            const currentCv = saveCvToArray(); // Collect sections as JSON
//
//            // Chuyển đổi `currentCv` thành danh sách đơn giản với `type` và `content` là chuỗi JSON
//            const sections = currentCv.map(section => ({
//                    type: section.type,
//                    content: JSON.stringify(section) // Chuyển đổi toàn bộ phần thành JSON chuỗi
//                }));
//        })();




        //Save to local storage when save button is clicked
//        $("#save-btn").click(function () {
//            let currentCv = saveCvToArray();
//            localStorage.setItem("CV", JSON.stringify(currentCv));
//            localStorage.setItem("theme", JSON.stringify(usedTheme));
//            //Display confirmation alert when saved
//            $("#save-alert").show("blind", 100);
//            setTimeout(function () {
//                $("#save-alert").hide("blind", 100);
//            }, 8000);
//        });

        $("#save-alert .close").click(function () {
            $("#save-alert").hide("blind", 100);
        });

        //Set default content when reset button is clicked and delete local storage
        $("#reset-btn").click(function () {
            setContent(defaultCv);
            changeTheme("theme-default");
            localStorage.clear();
        });



        //Fetch default CV data and display as formatted HTML
        function fetchCvData() {
            var urlParams = new URLSearchParams(window.location.search);
            var cvId = urlParams.get("cvId");
            console.log(cvId)
       
            fetch("/cv/get?cvId=" + cvId)
                    .then(response => response.json())
                    .then(data => {
                        defaultCv = data;  // Lưu dữ liệu mặc định
                console.log(data)
                        if (!data.sections) {
                            console.log("Không có CV lưu trữ sẵn");
                            return;
                        }

                        // Tạo nội dung từ dữ liệu JSON
                        setContent(data.sections);
                        usedTheme = data.usedTheme || "theme-default";  // Nếu không có theme, đặt theme mặc định
                        changeTheme(usedTheme);
                    })
                    .catch(error => console.log("Lỗi khi tải dữ liệu CV:", error));
        }

        $(document).ready(function () {
            $("#save-alert").hide();
            $("#new-section-buttons").hide();
            $("#close-section").hide();
            printable = $("#printable");

            // Tải dữ liệu CV
            fetchCvData();

            // Sự kiện nút lưu
            $("#save-btn").click(saveCvToServer);

            $("#save-alert .close").click(function () {
                $("#save-alert").hide("blind", 100);
            });

            $("#reset-btn").click(function () {
                setContent(defaultCv.sections);  // Dùng dữ liệu mặc định khi reset
                changeTheme("theme-default");
            });
        });

    });



})();





