package tech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import tech.entities.User;
import tech.services.UserService; // Bạn cần thêm lớp này để xử lý logic lưu vào DB
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tech.entities.HopDong;
import tech.entities.LopHoc;
import tech.services.HopDongService;
import tech.services.LopHocService;

@Controller
public class UserController {

    @Autowired
    private UserService userService; // Inject UserService để lưu người dùng vào DB
    @Autowired
    private HopDongService hopdongService;

    @Autowired
    private LopHocService lophocService;

    // GET request to show add user form
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String doGetAddUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";  // Tạo form thêm người dùng
    }

    // GET request to view user details (lấy dữ liệu người dùng)
    @RequestMapping(value = "/viewUser/{id}", method = RequestMethod.GET)
    public String doGetViewUser(@PathVariable("id") Integer id, Model model) {
        // Lấy thông tin người dùng từ database theo ID
        User user = userService.getUserById(id).orElse(new User());  // Nếu không tìm thấy người dùng, trả về đối tượng User mặc định
        model.addAttribute("user", user);
        return "view-user";  // Trang chi tiết người dùng
    }

    // POST request to process form data
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String doPostAddUser(@ModelAttribute("user") @Validated User user, BindingResult result) {
        // Kiểm tra lỗi khi submit form
        if (result.hasErrors()) {
            // In ra các lỗi trên console để debug
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "add-user";  // Trả lại trang add-user nếu có lỗi
        }

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        userService.saveUser(user);  // Giả sử UserService có phương thức saveUser() để lưu vào DB

        return "redirect:/viewUser/" + user.getId();  // Chuyển hướng đến trang xem người dùng sau khi lưu
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public String doGetEditUser(@PathVariable("id") Integer id, Model model) {
        // Lấy thông tin người dùng cần chỉnh sửa
        User user = userService.getUserById(id).orElse(new User()); // Nếu không tìm thấy người dùng, trả về đối tượng User mặc định

        model.addAttribute("user", user);

        return "edit"; // Trang chỉnh sửa người dùng
    }

    // POST request to update user details
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String doPostEditUser(@ModelAttribute("user") @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            // In ra các lỗi trên console để debug
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "edit";  // Trả lại trang edit-user nếu có lỗi
        }

        // Cập nhật thông tin người dùng trong cơ sở dữ liệu
        userService.updateUser(user);

        return "redirect:/viewUser/" + user.getId();  // Chuyển hướng đến trang xem người dùng sau khi cập nhật
    }

    // GET request to delete a user
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String doGetDeleteUser(@PathVariable("id") Integer id) {
        // Xóa người dùng theo id
        userService.deleteUser(id);

        return "redirect:/viewAllUsers";  // Chuyển hướng đến trang danh sách người dùng sau khi xóa
    }

    @RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
    public String doGetViewAllUsers(Model model) {
        List<User> users = userService.getAllUsers();  // Lấy danh sách người dùng từ DB
        model.addAttribute("users", users);  // Thêm danh sách người dùng vào mô hình
//        // Lấy lớp học của từng người dùng
//        for (User user : users) {
//            List<LopHoc> lopHocs = lophocService.getLopHocsByUserId(user.getId()); // Lấy danh sách lớp học của user
//            user.setLopHocs(lopHocs);  // Thêm danh sách lớp học vào đối tượng user
//        }
        model.addAttribute("classes", lophocService.getAll());

        return "viewall";  // Trang hiển thị danh sách người dùng và lớp học
    }

    @ModelAttribute("hopDongs")
    public List<HopDong> hopDongs() {
        return hopdongService.getAllHopDongs();
    }

    @ModelAttribute("lopHocs")
    public List<LopHoc> lopHocs() {
        return lophocService.getAll();
    }

    @PostMapping("/searchResults")
    public String search(@RequestParam(value = "searchByName", required = false) boolean searchByName,
            @RequestParam(value = "searchByPhone", required = false) boolean searchByPhone,
            @RequestParam(value = "searchByClass", required = false) boolean searchByClass,
            @RequestParam(value = "nameSearchTerm", required = false) String nameSearchTerm,
            @RequestParam(value = "phoneSearchTerm", required = false) String phoneSearchTerm,
            @RequestParam(value = "classSearchTerm", required = false) String classSearchTerm,
            Model model) {

        List<User> users = new ArrayList<>();  // Khởi tạo danh sách người dùng
        List<HopDong> hopDongs = new ArrayList<>();  // Khởi tạo danh sách hợp đồng

        // Kiểm tra điều kiện tìm kiếm
        if (searchByName && searchByPhone) {
            // Tìm kiếm theo cả tên và số điện thoại
            users = userService.searchByBoth(nameSearchTerm, phoneSearchTerm);
        } else if (searchByName) {
            // Tìm kiếm theo tên
            users = userService.searchUsersByName(nameSearchTerm);
        } else if (searchByPhone) {
            // Tìm kiếm theo số điện thoại
            users = userService.searchUsersByPhone(phoneSearchTerm);
        } else if (searchByClass) {
            // Tìm kiếm theo lớp học
            hopDongs = hopdongService.getHopDongByLopHocName(classSearchTerm);
            // Tạo danh sách người dùng từ các hợp đồng
            for (HopDong hopDong : hopDongs) {
                User user = hopDong.getUser();
                if (user != null && !users.contains(user)) {
                    users.add(user);  // Thêm người dùng vào danh sách nếu chưa có
                }
            }
        } else {
            // Nếu không chọn bất kỳ checkbox nào, trả về tất cả người dùng
            users = userService.getAllUsers();
        }

        // Thêm danh sách người dùng và hợp đồng vào model để hiển thị
        model.addAttribute("users", users);

        return "viewall";  // Trả về trang hiển thị kết quả tìm kiếm
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "test";  // Tên JSP view cho form thêm User
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "test";  // Trở lại form nếu có lỗi xác thực
        }
        // Lưu user vào cơ sở dữ liệu
        userService.saveUser(user);
        return "redirect:/viewAllUsers";  // Chuyển hướng đến danh sách user sau khi lưu thành công
    }
}
