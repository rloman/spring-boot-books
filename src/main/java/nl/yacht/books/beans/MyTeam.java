package nl.yacht.books.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyTeam {

    @Autowired
    private Person will;

    @Autowired
    private Person anne;

    public void pp() {
        System.out.println(will);
        System.out.println(anne);
    }

}
