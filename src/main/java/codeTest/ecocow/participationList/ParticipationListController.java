package codeTest.ecocow.participationList;

import codeTest.ecocow.movie.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/participations")
public class ParticipationListController {

    final ParticipationListService participationListService;
    final ParticipationListMapper participationListMapper;

    @PostMapping
    public ResponseEntity<ParticipationListDto> createParticipation(@RequestBody ParticipationListDto participationListDto) {
        ParticipationListDto createdParticipation = participationListService.createParticipationList(participationListDto);
        return new ResponseEntity<>(createdParticipation, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{participationListId}")
    public ResponseEntity getParticipationList(@PathVariable("participationListId") long participationListId) {
        ParticipationList participationList = participationListService.findVerifiedParticipationList(participationListId);

        return new ResponseEntity<>(participationListMapper.participationListToMovieParticipationListDto(participationList), HttpStatus.OK);
    }
    //del
    @DeleteMapping("/{participationListId}")
    public ResponseEntity deleteParticipationList(@PathVariable("participationListId") long participationListId) {
        participationListService.deleteParticipationList(participationListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
