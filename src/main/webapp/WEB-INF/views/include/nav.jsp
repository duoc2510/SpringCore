<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
    <nav id="navigation" class="navigation navigation-landscape">
        <div class="nav-header">
            <a class="nav-brand" href="#">
                <img src="../../assets/img/logo-light1.png" class="logo" alt="">
            </a>
            <div class="nav-toggle"></div>
            <ul class="mobile_nav dhsbrd">
                <li>
                    <div class="btn-group account-drop">
                        <button type="button" class="btn btn-order-by-filt" data-bs-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            <i class="fa-regular fa-comments"></i><span class="noti-status"></span>
                        </button>
                        <div class="dropdown-menu pull-right animated flipInX">
                            <div class="drp_menu_headr bg-primary">
                                <h4>Notifications</h4>
                            </div>
                            <div class="ntf-list-groups">
                                <div class="ntf-list-groups-single">
                                    <div class="ntf-list-groups-icon text-purple"><i
                                            class="fa-solid fa-house-medical-circle-check"></i></div>
                                    <div class="ntf-list-groups-caption"><p class="small"><strong>Kr. Shaury
                                                Preet</strong> Replied Your Message</p></div>
                                </div>
                                <div class="ntf-list-groups-single">
                                    <div class="ntf-list-groups-icon text-warning"><i
                                            class="fa-solid fa-envelope"></i></div>
                                    <div class="ntf-list-groups-caption"><p class="small">Mortin Denver Accepted
                                            Your Resume <strong class="text-success">On Job Stock</strong></p></div>
                                </div>
                                <div class="ntf-list-groups-single">
                                    <div class="ntf-list-groups-icon text-success"><i
                                            class="fa-solid fa-sack-dollar"></i></div>
                                    <div class="ntf-list-groups-caption"><p class="small">Your Job #456256
                                            Expired Yesterday <strong>View job</strong></p></div>
                                </div>
                                <div class="ntf-list-groups-single">
                                    <div class="ntf-list-groups-icon text-danger"><i
                                            class="fa-solid fa-comments"></i></div>
                                    <div class="ntf-list-groups-caption"><p class="small"><strong>Daniel
                                                kurwa</strong> Has Been Approved Your Resume!.</p></div>
                                </div>
                                <div class="ntf-list-groups-single">
                                    <div class="ntf-list-groups-icon text-info"><i
                                            class="fa-solid fa-circle-dollar-to-slot"></i></div>
                                    <div class="ntf-list-groups-caption"><p class="small">Khushi Verma Left A
                                            Review On <strong class="text-danger">Your Message</strong></p></div>
                                </div>
                                <div class="ntf-list-groups-single">
                                    <a href="#" class="ntf-more">View All Notifications</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="btn-group account-drop">
                        <button type="button" class="btn btn-order-by-filt" data-bs-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            <img src="../../assets/img/user-5.png" class="img-fluid circle" alt="">
                        </button>
                        <div class="dropdown-menu pull-right animated flipInX">
                            <div class="drp_menu_headr bg-primary">
                                <h4>Hi, ${user.email}</h4>
                                <div class="drp_menu_headr-right">
                                    <button type="button" class="btn btn-whites">Logout</button>
                                </div>
                            </div>
                            <ul>
                                <li><a href="candidate-dashboard.html"><i class="fa fa-tachometer-alt"></i>Dashboard<span
                                            class="notti_coun style-1">4</span></a></li>
                                <li><a href="candidate-profile.html"><i class="fa fa-user-tie"></i>My
                                        Profile</a></li>
                                <li><a href="candidate-resume.html"><i class="fa fa-file"></i>My Resume<span
                                            class="notti_coun style-2">7</span></a></li>
                                <li><a href="candidate-saved-jobs.html"><i class="fa-solid fa-bookmark"></i>Saved
                                        Resume</a></li>
                                <li><a href="candidate-messages.html"><i
                                            class="fa fa-envelope"></i>Messages<span
                                            class="notti_coun style-3">3</span></a></li>
                                <li><a href="candidate-change-password.html"><i class="fa fa-unlock-alt"></i>Change
                                        Password</a></li>
                                <li><a href="candidate-delete-account.html"><i
                                            class="fa-solid fa-trash-can"></i>Delete Account</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="nav-menus-wrapper">
            <ul class="nav-menu">
                <li><a href="${pageContext.request.contextPath}/index">Home<span class="submenu-indicator"></span></a>
                </li>
                <li><a href="JavaScript:Void(0);">Menu<span class="submenu-indicator"></span></a>
                    <ul class="nav-dropdown nav-submenu">
                        <li><a href="${pageContext.request.contextPath}/addUser">AddUser<span class="submenu-indicator"></span></a>

                        </li>
                        <li><a href="${pageContext.request.contextPath}/viewAllUsers">List<span
                                    class="submenu-indicator"></span></a>
                        </li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/tttc">Thông tin tiệc cưới<span class="submenu-indicator"></span></a>
                <li><a href="${pageContext.request.contextPath}/addTTTC">Add Thông tin tiệc cưới<span class="submenu-indicator"></span></a>

            </ul>
        </div>
    </nav>
</div>

