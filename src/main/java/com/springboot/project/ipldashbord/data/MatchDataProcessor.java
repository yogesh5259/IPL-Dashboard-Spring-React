package com.springboot.project.ipldashbord.data;

import java.time.LocalDate;

import com.springboot.project.ipldashbord.model.Match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    
    match.setMatchWinner(matchInput.getWinner());
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());

    //set team1 and team2
    String firstInningsTeam, secondInninTeam;
    if("bat".equals(matchInput.getToss_winner())){
      firstInningsTeam = matchInput.getToss_winner();
      secondInninTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
          ? matchInput.getTeam2() : matchInput.getTeam1();
    } else {
      secondInninTeam = matchInput.getToss_winner();
      firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
      ? matchInput.getTeam2() : matchInput.getTeam1();
    }

    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInninTeam);
    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());
    
    return match;
  }
}
