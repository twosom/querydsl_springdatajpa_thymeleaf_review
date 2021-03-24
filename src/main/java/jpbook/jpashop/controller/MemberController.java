package jpbook.jpashop.controller;

import jpbook.jpashop.controller.dto.MemberForm;
import jpbook.jpashop.controller.dto.MemberListDto;
import jpbook.jpashop.domain.Address;
import jpbook.jpashop.domain.Member;
import jpbook.jpashop.repository.MemberQueryRepository;
import jpbook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryRepository memberQueryRepository;


    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @ResponseBody
    @PostMapping(value = "/members/new")
    public void saveMember(@Valid @RequestBody MemberForm form) {

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getName(), address);

        memberService.join(member);
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<MemberListDto> members = memberQueryRepository.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
