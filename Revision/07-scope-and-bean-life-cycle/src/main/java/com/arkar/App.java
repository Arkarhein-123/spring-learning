package com.arkar;

import com.arkar.lazy_and_eager.CommentService;
import com.arkar.lazy_and_eager.UserService;
import com.arkar.lazy_and_eager.customer.CustomerService;
import com.arkar.lazy_and_eager.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
//        var cs1 = context.getBean(CustomerService.class);
//        var cs2 = context.getBean(CustomerService.class);
//        boolean isTrue = cs1 == cs2;
//        System.out.println("Is True : "+isTrue);
//        System.out.println("After retrieving customer Service");
//
          var cs1 = context.getBean(CommentService.class);
          var cs2 = context.getBean(UserService.class);

          if (cs1.getCommentRepository() == cs2.getCommentRepository()){
              System.out.println("True");
          }else {
              System.out.println("False");
          }


    }
}
