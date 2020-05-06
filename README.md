# slackbot
Slack RTM based bot. 

Slackbot is event driven and support cron tasks.

Below illustrate you a simple demo. It is so simple that just listen to some events and schedule cron tasks.
Now, I only implement the JSON parser and resolver. So you can only play with JsonEvent.
If you wanna interact with Slack, `methods` is helpful. `methods` implements some actions such as `postMessage` which can send message to certain channel you specified.
```java
String DEFAULT_TOKEN = "xxxx";
String DEFAULT_CHANNEL = "yyyy";

new Slack(DEFAULT_TOKEN,
    new SlackResolver<>(new BasicParser())
        .listen(RTMEvent.HELLO, (context) ->
            System.out.println(">connected to slack.")
        })
        .listen(RTMEvent.MESSAGE, (context) -> {
            System.out.println(">message.");
            JsonNode msg = context.event().getMessage();
            print(msg);
        })
        .listen(RTMEvent.GOODBYE, (context) -> /** blah... blah... */)
        .schedule("0 */1 * * * ?", (context) -> {
            String text = "Schedule Message: " + (new Date());
            context.methods().chatPostMessage(DEFAULT_CHANNEL, text, null, null);
        })
).connect();
```
Also, I supply you message materials. 

#### `Block` is rich-text
_They are a series of components that can be combined to create visually rich and compellingly interactive messages._
I implement `Context` `Image` `Divider` `Section` blocks.

#### Slack style `Markdown`
* escaping 
* basic formatting
    * italics, bold, strikethrough
    * line breaks
    * quotes, inline blocks, multiline blocks
    * lists
    * links
* advanced formatting
    * linking to public channels
    * mentioning users
    * mentioning groups
    * special mentions, @here @channel @everyone
    * date formatting