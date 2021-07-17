package com.example.security.springsecurity.account;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//問２－１ リポジトリを作成するアノテーションを記述

/*データ層のクラス*/
/*CrudRepositoryがCRUDの役割をするインターフェイスでusernameをアカウントリストから全件検索してください*/
/*どのワードで検索するのかというメソッドを追加します。*/
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public Account findByUsername(String username);
    public List<Account> findAll();
}