# Message_Schedular
 compile with javac -cp "C:\Users\surek\DP_Project\Message_Schedular\lib\*;." *.java Builder/*.java Factory/*.java Observer/*.java Singleton/*.java Main.java
### to access all dependencies of lib
 run with java -cp "C:\Users\surek\DP_Project\Message_Schedular\lib\*;." 
## Generate Telegram Bot API Key

To send messages to your Telegram chat using this application, follow these steps to create a bot and get your API key:

1. **Open Telegram** and search for **"BotFather"**.
2. Start a chat with BotFather and send the command `/newbot`.
3. Follow the prompts to name your bot and choose a username (it must end with `bot`).
4. **Copy the API token** provided by BotFather after the bot is created.
5. To get your chat ID:
   - Send a message to your bot.
   - Use the following URL in your browser (replace `<YourBotToken>` with your API token):
     ```
     https://api.telegram.org/bot<YourBotToken>/getUpdates
     ```
   - Look for `"chat": {"id": YOUR_CHAT_ID}` in the JSON response.
6. Update your application with the **API token** and **chat ID**.

## Usage

1. Ensure all dependencies are included.
2. Compile the Java files:
   ```bash
   javac -cp "lib/*" Factory/*.java Observer/*.java Builder/*.java Main.java