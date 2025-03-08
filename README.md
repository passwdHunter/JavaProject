Class Structure:
User

firstName: String

lastName: String

birthDate: Date

gender: String

location: String

interests: List<String>

photos: List<String>

bio: String

Message

sender: User

recipient: User

content: String

sentDate: Date

Match

user1: User

user2: User

matchDate: Date

Chat

participants: List<User>

messages: List<Message>

startDate: Date

MatchPreferences

user: User

preferredGender: String

preferredAgeRange: String

preferredLocation: String

UserStatistics

user: User

numberOfMatches: int

numberOfMessagesSent: int

numberOfMessagesReceived: int

mostActiveChatPartner: User

Связи между классами:
Пользователь может отправлять и получать сообщения, участвовать в чатах и иметь совпадения с другими пользователями.

MatchPreferences связывает пользователя с его предпочтениями.

UserStatistics отслеживает активность пользователя в приложении.

Example README.md for your project:
markdown
# Dating App

This project is a dating application. The main features of the app include creating user profiles, sending and receiving messages, and matching with other users based on preferences.

## Classes

### User
The `User` class represents a user profile in the app. It contains information such as first name, last name, birth date, gender, location, interests, photos, and bio.

### Message
The `Message` class is used to store text messages sent and received by users.

### Match
The `Match` class stores information about matches between users.

### Chat
The `Chat` class stores information about chats between users, including participants and messages.

### MatchPreferences
The `MatchPreferences` class stores a user's preferred match criteria, including preferred gender, age range, and location.

### UserStatistics
The `UserStatistics` class tracks a user's activity within the app, such as the number of matches, messages sent and received, and most active chat partner.
