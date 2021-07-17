package com.example.security.springsecurity.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//実行時に宣言した各フィールド変数がカラムとして作成される
//問１－１ DB設計に必要なアノテーションを記述
/*アカウントjavaはデータベースにデータを保存する役割*/
/*JPAは、データベース上の表をjavaで使えるようにする仲介業者*/
@Entity/*JPAのデータベースに保管するオブジェクトを示す*//*実体。全体を表す箱のようなもの*/
@Table(name="accounts")/*テーブルの名前をaccountsにする*/
public class Account implements UserDetails {

	/*デフォルト・シリアルバージョンIDの追加*/
	private static final long serialVersionUID = 1L;
	
	//権限は一般ユーザ、マネージャ、システム管理者の３種類とする
	public enum Authority {ROLE_USER, ROLE_MANAGER, ROLE_ADMIN}

	//問１－２ プライマリーキーを設定するアノテーションを記述
	@Id/*@EmbeddedId*//*プライマリーキーを設定する*/
	@Column(nullable = false, unique = true)/*カラムの中身を指定。非NULL,他に同じ値が無い*/
	private String username;

	@Column(nullable = false)/*カラムの中身を指定。非NULL*/
	private String password;

	@Column(nullable = false, unique = true)/*カラムの中身を指定。非NULL,他に同じ値が無い*/
	private String mailAddress;

	@Column(nullable = false)/*カラムの中身を指定。非NULL*/
	private boolean mailAddressVerified;

	@Column(nullable = false)/*カラムの中身を指定。非NULL*/
	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)/*TemporalTypeのTIMESTAMPを読取り*/
	private Date createdAt;/*現在日時を設定する*/

	// roleは複数管理できるように、Set<>で定義。
	@ElementCollection(fetch = FetchType.EAGER)/*基本型（Entityじゃないクラス）をもつCollectionやListの属性を別のテーブルに保存してくれるようになる。*/
	@Enumerated(EnumType.STRING)/*Enum の値を DB に格納することを宣言するアノテーション*/
	@Column(nullable = false)/*カラムの中身を指定。非NULL*/
	private Set<Authority> authorities;

	// JPA requirement
	protected Account() {}

	//コンストラクタ
	public Account(String username, String password, String mailAddress) {
		this.username = username;
		this.password = password;
		this.mailAddress = mailAddress;
		this.mailAddressVerified = false;
		this.enabled = true;
		this.authorities = EnumSet.of(Authority.ROLE_USER);
	}

	//登録時に、日時を自動セットする
	@PrePersist/*データベースにINSERT文を発行する前に呼び出されるコールバックメソッドであることを示すアノテーション*/
	public void prePersist() {
		this.createdAt = new Date();
	}

	//admin権限チェック
	public boolean isAdmin() {
		return this.authorities.contains(Authority.ROLE_ADMIN);
	}

	//admin権限セット
	public void setAdmin(boolean isAdmin) {
		if (isAdmin) {
			this.authorities.add(Authority.ROLE_MANAGER);
			this.authorities.add(Authority.ROLE_ADMIN);
		} else {
			this.authorities.remove(Authority.ROLE_ADMIN);
		}
	}

	//管理者権限を保有しているか？
	public boolean isManager() {
		return this.authorities.contains(Authority.ROLE_MANAGER);
	}

	//管理者権限セット
	public void setManager(boolean isManager) {
		if (isManager) {
			this.authorities.add(Authority.ROLE_MANAGER);
		} else {
			this.authorities.remove(Authority.ROLE_MANAGER);
			this.authorities.remove(Authority.ROLE_ADMIN);
		}
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Authority authority : this.authorities) {
			authorities.add(new SimpleGrantedAuthority(authority.toString()));
		}
		return authorities;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	/*@Overrideすることにより、*/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public String getUsername() {
		return username;
	}
	/*usernameを書き換えする*/
	public void setUsername(String username) {
		this.username = username;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public String getPassword() {
		return password;
	}
	/*passwordを書き換えする*/
	public void setPassword(String password) {
		this.password = password;
	}

	/*クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと*/
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/*enabledの書き換えをする*/
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/*メールアドレスを取得する*/
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public boolean isMailAddressVerified() {
		return mailAddressVerified;
	}
	public void setMailAddressVerified(boolean mailAddressVerified) {
		this.mailAddressVerified = mailAddressVerified;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
}