package net.egem.blog;

import net.egem.blog.model.*;
import net.egem.blog.repository.service.*;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.faces.webapp.FacesServlet;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.EnumSet;

@EnableAutoConfiguration
@EnableScheduling
@ComponentScan({"net.egem.blog"})
@PropertySource("classpath:application.properties")
public class BlogApplication extends SpringBootServletInitializer {


    @Inject
    private UserService userService;
    @Inject
    private ArticleService articleService;
    @Inject
    private CommentService commentService;
    @Inject
    private ContactService contactService;
    @Inject
    private TagService tagService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean<FacesServlet>(servlet, "*.jsf");
    }

    @Bean
    public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
        FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
        rwFilter.setDispatcherTypes(
                EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
        rwFilter.addUrlPatterns("/*");
        return rwFilter;
    }

    @Bean
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new RewriteFilter());
        return prettyFilter;
    }

    @Bean
    public FilterRegistrationBean FileUploadFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
        registration.setName("PrimeFaces FileUpload Filter");
        registration.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registration;
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilterDisabled(
            @Qualifier("hiddenHttpMethodFilter") HiddenHttpMethodFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    InitializingBean startup() {
        return () -> {

            System.out.println("Spring boot blog project startup method begin...");

            User user = new User();
            User user1 = new User();

            String path1 = "C:\\Users\\drsnkrt\\Pictures\\im.jpg";
            String path2 = "C:\\Users\\drsnkrt\\Pictures\\cv.jpg";


            File file = new File(path1);
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            user.setImage(imageInByte);
            baos.close();

            File file2 = new File(path2);
            BufferedImage image2 = ImageIO.read(file2);
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ImageIO.write(image2, "jpg", baos2);
            baos2.flush();
            byte[] imageInByte2 = baos2.toByteArray();
            user1.setImage(imageInByte2);
            baos2.close();


            user.setName("dursun");
            user.setPassword("123123");
            user.setSurname("kurt");
            user.setEmail("dk@krt.net");
            userService.save(user);
            System.out.println(user.getName() + " User created...");


            user1.setName("murat");
            user1.setPassword("123123");
            user1.setSurname("kurt");
            user1.setEmail("mk@krt.net");
            userService.save(user1);
            System.out.println(user1.getName() + " User created...");

            Article article = new Article();
            article.setTitle("YAZI 1");
            article.setArticle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla malesuada enim id enim congue convallis. Praesent a cursus orci. Proin mauris eros, rhoncus in risus nec, vestibulum dignissim diam. Duis dapibus, magna ac fringilla consectetur");
            article.setImage(imageInByte2);
            article.setRecordDate(new Date());
            article.setCount(11);
            articleService.save(article);
            System.out.println(article.getTitle() + " Article created...");
            commentService.save(new Comment("comment1", article));
            commentService.save(new Comment("comment2", article));
            commentService.save(new Comment("comment3", article));
            tagService.save(new Tag("tag1,tag2,tag3,tag4,tag5", article));

            Article article2 = new Article("Yazı 2", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte2, 0);
            Article article3 = new Article("Yazı 3", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article4 = new Article("Yazı 4", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article5 = new Article("Yazı 5", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article6 = new Article("Yazı 6", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article7 = new Article("Yazı 7", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article8 = new Article("Yazı 8", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            Article article9 = new Article("Yazı 9", "at enim. Praesent at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt an at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt at enim. Praesent pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant ant t pretium ullamcorper quam, at rhoncus magna consectetur quis. Nulla condimentum, libero vel varius sodales, lacus urna accumsan purus, at mattis nisi nibh in lorem. Sed laoreet, ante vitae tincidunt auctor, sapien metus tincidunt ant", new Date(), imageInByte, 3);
            articleService.save(article2);
            articleService.save(article3);
            articleService.save(article4);
            articleService.save(article5);
            articleService.save(article6);
            articleService.save(article7);
            articleService.save(article8);
            articleService.save(article9);
            System.out.println(article2.getTitle() + " Article created...");
            tagService.save(new Tag("tag11,tag22,tag33,tag44,tag55", article2));
            articleService.save(article3);
            System.out.println(article3.getTitle() + " Article created...");

            Contact contact = new Contact("apploader", "app@loader.net", "load", "Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı", false);
            Contact contac2 = new Contact("loader", "load@loader.net", "app", "Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı ", false);
            if (contactService.save(contact)) {
                System.out.println(contact.getEmail() + " created");
            } else {
                System.out.println(contact.getEmail() + " unsuccess");
            }

            if (contactService.save(contac2)) {
                System.out.println(contac2.getEmail() + " created");
            } else {
                System.out.println(contac2.getEmail() + " unsuccess");
            }
            for (Comment comment : commentService.getCommentsByAritcleId(3)) {
                System.out.println(comment.getComment());
            }


            System.out.println("Spring boot blog project startup method end...");
        };
    }

}
