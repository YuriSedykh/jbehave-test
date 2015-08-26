Google test

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  Open Google and make search
Given open URL 'http://google.com' in browser 'firefox'
When put text 'JBehave' to textbox 'SearchBox' on form 'GoogleSearch'
And click button 'Search' on form 'GoogleSearch'
!-- Then step represents the outcome of the event