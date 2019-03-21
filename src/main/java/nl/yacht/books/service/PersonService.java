package nl.yacht.books.service;

import nl.yacht.books.beans.MyTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private MyTeam team;

    public void pp() {
        this.team.pp();
    }
}
