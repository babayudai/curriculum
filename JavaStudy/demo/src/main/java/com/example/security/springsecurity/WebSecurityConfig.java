package com.example.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.springsecurity.account.AccountService;

/*認証情報を管理しているクラス*/
@EnableWebSecurity/*アプリケーションがユーザー名とパスワードの入力を求めるということです（デフォルトの動作）*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Autowiredは別のクラスを使えるようにしてくれるもの*/
	@Autowired
	private AccountService userService;
	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()/*認証されたユーザーの認証情報を返却*/
		.antMatchers("/login", "/login-error").permitAll()/*指定したパスパターンに一致するリソースを適用対象にする*/
		.antMatchers("/**").hasRole("USER")/*指定したパスパターンに一致するリソースを適用対象にする*//*引数に指定したロール=権限詰め合わせセットのこと。を保持している場合にtrueを返却する*/
		.and()/*かつ*/
		.formLogin()/*フォーム認証の適用*/
		.loginPage("/login").failureUrl("/login-error");/*カスタムログインページへの遷移指定.カスタムログインページへの遷移不可時の遷移先指定*/
	}


	//変更点 ロード時に、「admin」ユーザを登録する。
	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth/*OKな認証する*/
		.userDetailsService(userService)/*認証するユーザーを設定する*/
		.passwordEncoder(passwordEncoder());/*パスワードをハッシュ化*/

		if (userService.findAllList().isEmpty()) {
			userService.registerAdmin("admin", "secret", "admin@localhost");
			userService.registerManager("manager", "secret", "manager@localhost");
			userService.registerUser("user", "secret", "user@localhost");
		}
	}
	//変更点 PasswordEncoder(BCryptPasswordEncoder)メソッド
	/*DIコンテナに管理させたい「Bean」を生成するメソッドに付与する*/
	/*passwordEncoder()にBeanとしてシングルトンパターンにBCryptPasswordEncoderという新しくメソッドを作る*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		//
		return new BCryptPasswordEncoder();
	}

}