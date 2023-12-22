import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8Object;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.Test;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JSTest {
    private final File script = Paths.get("src/test/resources/scripts/test.js").toFile();
    private final File tiktok_signature = Paths.get(".")
            .toAbsolutePath()
            .getParent()
            .getParent()
            .resolve("tiktok-signature-master")
            .toFile();
    private final File tiktok_signature_npm = Paths.get(".")
            .toAbsolutePath()
            .getParent()
            .getParent()
            .resolve("tiktok-signature-master")
            .resolve("node_modules")
            .toFile();
    private final File tiktok_signature_browser = tiktok_signature.toPath()
            .resolve("browser.js")
            .toFile();

    @Test
    public void executeScriptEngine() throws IOException, ScriptException, NoSuchMethodException {
        var scriptEngineManager = new ScriptEngineManager();
        var engine = scriptEngineManager.getEngineByName("JavaScript");

        engine.eval(Files.newBufferedReader(script.toPath()));

        var invocable = (Invocable) engine;
        var result = invocable.invokeFunction("hello");

        System.out.println(result);
    }

    @Test
    public void executeBrowser() throws IOException, ScriptException, NoSuchMethodException {
        System.getProperties().put("polyglot.js.commonjs-require", "true");
        System.getProperties().put("polyglot.js.commonjs-require-cwd", tiktok_signature_npm.toPath().toAbsolutePath().toString());

        var engine = GraalJSScriptEngine.create(
                null,
                Context.newBuilder("js")
                        .allowAllAccess(true)
        );
        engine.getContext().setAttribute("out", System.out, ScriptContext.ENGINE_SCOPE);
        Object a = engine.eval(Files.newBufferedReader(tiktok_signature_browser.toPath()));

        System.out.println(engine.get("a")); // InterpretedFunction
        var bindings = new SimpleBindings();
        //bindings.put("polyglot.js.allowAllAccess", true);
        bindings.put("foobar", engine.get("a"));
        engine.eval("foobar();", bindings); // hello from JavaScript
        engine.eval("foobar();"); // in foobar() definition
    }
}