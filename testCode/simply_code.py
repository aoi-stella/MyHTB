import http.client
import json

# ログイン情報を設定する
login_payload = {
    'email': 'xxx',
    'password': 'yyy',
    'remember':'true'
}

# ログインリクエストを作成する
login_conn = http.client.HTTPSConnection("www.hackthebox.com")
login_headers = {
    'Content-Type': 'application/json'
}

login_conn.request("POST", "/api/v4/login", json.dumps(login_payload), login_headers)

# ログインレスポンスからトークンを取得する
login_res = login_conn.getresponse()
login_data = login_res.read()
login_json = json.loads(login_data.decode("utf-8"))
auth_token = login_json['message']['access_token']

# ログイン後にAPIにアクセスするリクエストを作成する
api_conn = http.client.HTTPSConnection("www.hackthebox.com")
api_headers = {
    'Authorization': f'Bearer {auth_token}'
}

# 使用したいエンドポイント
api_conn.request("GET", "/api/v4/user/profile/basic/1356238", headers=api_headers)

# APIからのレスポンスを取得する
api_res = api_conn.getresponse()
api_data = api_res.read()
print(api_data.decode("utf-8"))
