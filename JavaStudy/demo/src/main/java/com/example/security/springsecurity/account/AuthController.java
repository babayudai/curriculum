package com.example.security.springsecurity.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//問４－１ コントローラーを意味するアノテーションを記述
@Controller/*コントローラーの働きをする*/
public class AuthController {

	@Autowired/*@Autowiredは別のクラスを使えるようにしてくれるもの*/
	private AccountRepository repository;

	/*List<Account>を全検索してリターンしてください*/
	public List<Account> get() {
		return (List<Account>) repository.findAll();
	}
	/*アノテーションに続くカッコにリクエストのパスを指定する。
	メソッドはString型で定義し、表示（または遷移）させたい画面のテンプレートファイル名を文字列としてreturnする。*/
	/*マッピングとは、アレとコレを関連付けるもの*/
	@RequestMapping("/")/*/topに遷移してください*/
	public String index() {
		return "redirect:/top";/*リダイレクト先を相対URLで指定*//*リダイレクトはそこに行くしかありません*/
	}

	@GetMapping("/login")/*login.htmlに遷移してください*/
	public String login() {
		return "login";
	}

	@PostMapping("/login")/*login*/
	public String loginPost() {/*/login-errorに飛んでください*/
		return "redirect:/login-error";/*リダイレクトで/login-errorに行くしかありません*/
	}

	@GetMapping("/login-error")/*ログインエラー*/
	public String loginError(Model model) {/*/login-errorはログインエラーになった時、ログインページに戻ってください*/
		model.addAttribute("loginError", true);
		return "login";/*ログインページに戻ってください*/
	}

	@RequestMapping("/top")
	public String top() {/*topのページに行ってください*/
		return "/top";/*そこに行ってください*/
	}

}