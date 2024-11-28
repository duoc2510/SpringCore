package tech.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// ControllerAdvice xử lý các ngoại lệ toàn cục trong ứng dụng
@ControllerAdvice
public class ExceptionControllerAdvice {

    // Xử lý MyException - với thông báo lỗi tuỳ chỉnh
    @ExceptionHandler(MyException.class)
    public ModelAndView handleMyException(MyException mex) {
        ModelAndView model = new ModelAndView();
        
        // Thêm thông tin về mã lỗi và thông báo lỗi tuỳ chỉnh
        model.addObject("errCode", mex.getErrCode());
        model.addObject("errMsg", "Lỗi MyException: " + mex.getMessage());
        model.setViewName("error");  // Chuyển hướng đến trang error.jsp
        return model;
    }

    // Xử lý Exception chung - với thông báo lỗi tuỳ chỉnh
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView model = new ModelAndView();
        
        // Thêm thông tin về lỗi chung
        model.addObject("errMsg", "Lỗi chung: " + ex.getMessage());
        model.setViewName("error");  // Chuyển hướng đến trang error.jsp
        return model;
    }
}
