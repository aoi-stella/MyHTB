<p float="left">
  <img src="https://github.com/hal-art/MyHTB/assets/113904272/c442e717-76f4-48be-8064-1483789d3046" width="250" />
  <img src="https://github.com/hal-art/MyHTB/assets/113904272/7bb4c81c-a209-4ccd-995d-083d062610cb" width="250" /> 
</p>


## 目次
- [概要](#概要)
- [機能](#機能)
- [技術スタック](#技術スタック)
- [データ構造](#データ構造)
- [使用方法](#使用方法)
- [ライセンス](#ライセンス)
- [連絡先](#連絡先)
- [プライバシーポリシー](#プライバシーポリシー)

## 概要
本アプリは、サイバーセキュリティ学習用サイトである「HackTheBox」のAPIを用いて、  
Webサイトにアクセスすることなくユーザー情報を確認できるようにしたアプリです。

## 機能
- ログイン機能: ユーザー情報の取得の為必要なログイン処理を行う機能です
- ユーザー情報表示機能:現在、以下のユーザー情報を表示することが可能です。
  * ユーザー名
  * Emailアドレス
  * プロフィールアイコン
  * フォロワー数
  * サブスクリプション状態(VIPかどうか)
  * マシンへの接続状態
  * 現在のランク
  * 次のランク
  * 次のランクへの必要なポイント

## 技術スタック
- 言語
  * Kotlin
  * XML
- アーキテクチャ
  * MVVM
  * Repository
- ライブラリ
  * Retrofit
  * Gson

##  データ構造
![名称未設定ファイル drawio](https://github.com/hal-art/MyHTB/assets/113904272/7d5e247b-bec0-468d-bf07-2c3839c29c30)  
- HackTheBox Server  
  →マスタデータを管理しています。基本的に本サーバと通信をしてデータを取得します。

- Retrofit(Service)  
  →HackTheBox Serverと通信を行うServiceインターフェースです。Retrofitライブラリを用いています。

- Repository  
  →Serviceを用いてデータを取得及びキャッシュします。必要に応じてModelにデータを引き渡します。  
  →ServiceではResponse<ResponseBody>オブジェクトで結果が返ってきますがそれらから該当データを抽出して保持する役割もここが担います。

- xxModel  
  →※xxは画面名が入ります。(Login等)  
  →ViewModelに対しデータを引き渡します。Viewに表示できるように変換する処理以外はこのクラスが担います。

- xxViewModel  
  →※xxは画面名が入ります。(Login等)  
  →Modelから取得したデータをViewで表示できるように加工をします。

- xxView  
  →※xxは画面名が入ります。(Login等)  
  →データバインドの処理などの初期処理を記載します。  
  →基本的に各画面のコードビハインドに手を加えないようにしています。


## 使用方法  
※例としてUser Info画面で紹介します  
1. apkをGooglePlayStore(URL:)からインストールします。
2. アプリを起動します。
3. ログイン画面にEmailアドレス及びパスワードを入力します。
4. ログインボタンを押下します
5. ログイン処理完了後、"Connected"と表示されていれば手順7.へ移ってください。
6. ログイン処理完了後、"No Connection"と表示された場合は、ログイン情報が正しいかどうか確認してください。
7. 画面左上のハンバーガーボタンを押下しナビゲーションバーを表示させます。
8. "User Info"画面を開きます。
9. 画面下部の"Reload"ボタンを押下します。
10. 取得に成功すると、画面のユーザー情報が更新されます。

## ライセンス
MIT

## 連絡先
- 開発者名: halsec
- メール: halcorder@gmail.com

## プライバシーポリシー

halsec built the HackTheBoxMobile app as an Open Source app. This SERVICE is provided by halsec at no cost and is intended for use as is.

This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.

If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.

The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which are accessible at HackTheBoxMobile unless otherwise defined in this Privacy Policy.

**Information Collection and Use**

For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information, including but not limited to None. The information that I request will be retained on your device and is not collected by me in any way.

The app does use third-party services that may collect information used to identify you.

Link to the privacy policy of third-party service providers used by the app

*   [Google Play Services](https://www.google.com/policies/privacy/)

**Log Data**

I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.

**Cookies**

Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.

This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.

**Service Providers**

I may employ third-party companies and individuals due to the following reasons:

*   To facilitate our Service;
*   To provide the Service on our behalf;
*   To perform Service-related services; or
*   To assist us in analyzing how our Service is used.

I want to inform users of this Service that these third parties have access to their Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.

**Security**

I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.

**Links to Other Sites**

This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.

**Children’s Privacy**

These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13 years of age. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do the necessary actions.

**Changes to This Privacy Policy**

I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page.

This policy is effective as of 2023-07-04

**Contact Us**

If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me at halcorder@gmail.com.

This privacy policy page was created at [privacypolicytemplate.net](https://privacypolicytemplate.net) and modified/generated by [App Privacy Policy Generator](https://app-privacy-policy-generator.nisrulz.com/)
