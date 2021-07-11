package survey.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import survey.models.AnsweredData;
import survey.models.SurveyQuestion;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	/**
	 * Create Date : 2021-06-12
	 * Author : 박기태
	 * Copyrighter : 박기태
	 * 
	 * Action
	 * 		설문조사 항목 생성	
	 * 
	 * @return 설문항목 리스트 : ArrayList
	 */
	private List<SurveyQuestion> createQuestions() {
		SurveyQuestion sq1 = new SurveyQuestion("당신의 역할은 무엇입니까?", Arrays.asList("백엔드","프론트엔드","풀스택"));
		SurveyQuestion sq2 = new SurveyQuestion("많이 사용하는 개발도구는 무엇입니까?", Arrays.asList("이클립스","인텔리J","노트패드++"));
		SurveyQuestion sq3 = new SurveyQuestion("하고 싶은 말을 적어주세요.");
		
		return Arrays.asList(sq1, sq2, sq3);		
	}
	
	/**
	 * Create Date : 2021-06-12
	 * Author : 박기태
	 * Copyrighter : 박기태
	 * 
	 * Action
	 * 		설문조사 폼을 보여줌
	 * 
	 * @return 설문조사 폼 jsp페이지 문자열
	 */
	@GetMapping
	public String surveyForm(Model model) {
		model.addAttribute("questions", createQuestions());
		
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String surveySubmit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
