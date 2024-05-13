package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.ActorService;
import kr.co.moviespring.web.service.DirectorService;
import kr.co.moviespring.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("people")
public class PeopleController {
    @Autowired
    ActorService actorService;
    @Autowired
    DirectorService directorService;
    @Autowired
    MovieService movieService;

    @GetMapping("info")
    public String info(@RequestParam(name="type",required = false)String type,
                       @RequestParam(name="name",required = false)String name,
                       @RequestParam(name="people-id",required = false)Long peopleId,
                       @RequestParam(name="id",required = false)String tmdbId,
                         Model model) {

        List<Movie> list = null;

        if ("actor".equals(type)) {
            Actor actor = actorService.getByTMDBId(tmdbId);
            model.addAttribute("people", actor);
            list = movieService.getListByPeopleId(peopleId, type);
        }
        if ("director".equals(type)) {
            Director director = directorService.getByTMDBId(tmdbId);
            model.addAttribute("people", director);
            list = movieService.getListByPeopleId(peopleId, type);
        }

        model.addAttribute("list", list);

        return "people/info";
    }
}
