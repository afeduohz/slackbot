import com.defa.slack.message.Blocks;
import com.defa.slack.message.Markdown;
import com.defa.slack.message.builder.ImageElementBuilder;
import com.defa.slack.message.builder.MarkdownTextObjectBuilder;
import com.defa.slack.message.builder.PlainTextObjectBuilder;

public class BlocksTest {

    //output directly to `Block Kit Builder`
    public static void main(String[] args) {
        System.out.println(
                Blocks.create()
                        .divider()
                        .section("This is test stream:")
                        .divider()
                        .section(Markdown.create().quote("hi there,").quote("It's a nice day."))
                        .image("https://a.slack-edge.com/80588/img/slack_api_logo_vogue.png", "image", "i am pic.")
                        .context(
                                PlainTextObjectBuilder.builder().text("hello, world!").build(),
                                ImageElementBuilder.builder().url("https://image.freepik.com/free-photo/red-drawing-pin_1156-445.jpg").alt("alt me").build(),
                                MarkdownTextObjectBuilder.builder().text(Markdown.create().quote("yes")).build()
                        )
                        .format()
        );
    }
}
