package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.service.ActorService;
import kr.co.moviespring.web.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("people")
public class PeopleController {
    @Autowired
    ActorService actorService;
    @Autowired
    DirectorService directorService;

    @GetMapping("info")
    public String info(@RequestParam(name="name",required = false)String name,
                         @RequestParam(name="id",required = false)String tmdbId,
                         Model model) {
//        th:href="@{/people(id=${director.id},name=${director.korName})}"
//        List<Movie> mList = movieService.getListByName(query);
        Actor actor = actorService.getByTMDBId(tmdbId);
        model.addAttribute("people", actor);
        if (actor == null) {
        Director director = directorService.getByTMDBId(tmdbId);
            model.addAttribute("people", director);
        }

////        model.addAttribute("query", query);
//        model.addAttribute(("dList"), dList);
//        model.addAttribute(("aList"), aList);
        return "people/info";
    }
}
