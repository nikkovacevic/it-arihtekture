//package ita.ms.properties.utils;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && jwtUtil.validateToken(token)) {
//            return true;
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
//            return false;
//        }
//    }
//
//}
