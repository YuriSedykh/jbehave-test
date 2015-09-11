					 
Scenario:  Login to Nigtingale
Given open URL 'https://miratech-sandbox.nexia.local/' in browser 'firefox'
When put text '***' to textbox 'Account' on form 'NightingaleLogin'
And put text '***' to textbox 'UserID' on form 'NightingaleLogin'
And put text '***' to textbox 'Password' on form 'NightingaleLogin'
And click button 'SignIn' on form 'NightingaleLogin'
Then should open form 'NightingaleFrontDesk'
