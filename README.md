CSC207-Group-Project
group members:
- Ian (github name: iExploze)
- Anson (github name: anslau)
- Ricky (github name: LogicCTDT)
- Flora (github name: mangolala1)

Note: To run the program on IntelliJ, you must add the jarfiles in the jar_files folder as dependencies. They are part of an old swing feature that implemented autocomplete. It has since been removed in Java 8. Additionally, the files can be found at this website:
https://jar-download.com/artifacts/org.swinglabs.swingx/swingx-autocomplete/1.6.5-1/source-code/org/jdesktop/swingx/autocomplete/AutoCompleteDecorator.java

Work flow of the team:

- Quick discussion/ detail discussion with group
- Divide work up clearly
- Communicate clearly what we're working on and what information we need.
- Do implementation
- Submit pull request for it and do a review on it
- Delete/pull branch
- repeat.
  
Entities/use cases implemented by everyone on the team:

- signup - Ricky [Done]
- login - Ricky [Done]
- play song - Flora [Done]
- skip song - Ian [Done]
- guess song - Anson [Done]
- API authentication - Ian & Ricky [Done]
- Timer - Ian [Done]
- Score counter - Ian [Done]
- Score popup - Anson [Done]
- Login - Ricky [Done]
- AutoCompleteTextBox - Ricky [Done]
- AutoComplete Suggestion - Ricky [Done]
- Quiz instantiation - Ian [Done]
- Song Progress Bar - Anson [Done]
- Testing - Everyone [Done]
  

**A description of the problem domain your team is tentatively wanting to focus 
on in the project. (e.g., trivia, finance, real estate, etc…):**
Our application is primarily centered around music trivia and is targeted toward 
people looking to test their knowledge of songs and lyrics or want to gain exposure 
to songs other genres of music. Inspired by Anime Music Quiz, an online browser 
game that allows players to guess the anime based on its opening song, our group 
wanted to develop a similar service after noticing there wasn't an alternative 
trivia option for other genres of music. Using our application, users have the 
flexibility to create quizzes based on their Spotify playlist, expanding the song 
options they are able to choose from.

**A brief, high-level description of what kind of application your team is thinking 
of developing:**
Our team has tentatively decided on developing a web application that allows users 
to guess music and song names based on a short audio track. Players can connect their 
account to their Spotify login where they can generate quizzes based on their own 
custom playlist.

**Current implementation of the project:**
In single-player mode, a user will be able to play with their favourite/top 50 songs 
from their Spotify current Spotify data.

**This part is not implemented in the program:**
========================================================================================================================
In multiplayer mode, users will be given a unique ID that can be shared with others 
(i.e., friends, family, colleagues) to create group-friendly competitions. Groups 
can compete and play premade quizzes as described above, but will also be given the 
option of choosing playlists from their own Spotify accounts to form the personalized 
song pools. Points will be distributed to each player based on the amount of time 
taken to guess the song (note: no points will be deducted for incorrect guesses). 
The player with the most points at the end will be the winner.
In ranked mode, players will have the opportunity to compete in a competitive match to
earn Musical Points (MP) that will contribute to their overall score on the global 
leaderboard. The system will pair players against each other based on relative skill 
with MP being the primary determinant. Similar to the single-player mode, quizzes will 
be premade and will vary across genres and difficulty levels to ensure fairness and 
objectivity. 

========================================================================================================================

Link to the Spotify API: https://developer.spotify.com/documentation/web-api.
We obtained an access token to the spotify API and made a Get request 
to obtain information about an artist named Radiohead. Below is the screenshot
of calling API using postman.

![](photos/API_screenshot.jpeg)
<img width="1470" alt="Screenshot 2023-10-01 at 09 36 13" src="https://github.com/iExploze/Song-Guesser/assets/54289660/80c2a1ba-a008-40d9-925b-bbf3147c4031">
