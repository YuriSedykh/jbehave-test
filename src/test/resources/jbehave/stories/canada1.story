Narrative:
Add a field for Sexual orientation to the socio-economics tab in patient demographics. 
This is a dropdown list that contains the following entries:
Bisexual
Gay
Heterosexual ("straight")
Lesbian
Queer
Two-Spirit
Other (Please specify) - OPEN TEXT field “Other” appears below and user is able to enter a value there
Prefer not to answer
Do not know
The user can select only one selection. 
The field is not required.

Scenario: Check added field for Sexual orientation to the socio-economics tab in patient demographics
Given Login as 'User Name'
When Find patient 'Jon Smith'
And Open patient registration page
And Open 'Socio-Economics' tab
And Click button 'Edit'
Then Field 'SexualOrientation' should present
When Click field 'SexualOrientation'
Then Dropdown list 'SexualOrientation' should enabled
And Dropdown list 'SexualOrientation' should content:
|Content|
|Bisexual|
|Gay|
|Heterosexual|
|Lesbian|
|Queer|
|Two-Spirit|
|Other|
|Prefer not to answer|
|Do not know|
