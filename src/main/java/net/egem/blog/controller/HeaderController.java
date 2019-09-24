package net.egem.blog.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Scope(value = "session")
@Component(value = "headerController")
@ManagedBean(name = "headerController")
public class HeaderController {

    public String getDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        return "  " + strDate;
    }

}
