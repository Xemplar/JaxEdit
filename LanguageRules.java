import java.awt.*;
import java.util.*;
import javax.swing.text.*;

public abstract class LanguageRules{
	//Languages
	public static final LanguageRules JAVA = new LangJava();
	
	private final ArrayList<WordColorPair> pairs;
	private final StyleContext context;
	
    private LanguageRules(){
		context = StyleContext.getDefaultStyleContext();
		pairs = new ArrayList<WordColorPair>();
		
		pairs.add(new WordColorPair(this.context, "", 0x000000));
	}
	
	public StyleContext getContext(){
		return context;
	}
	
	public ArrayList<WordColorPair> getPairs(){
		return pairs;
	}
	
	public final void createWordRule(String word, int color){
		pairs.add(new WordColorPair(this.context, "(\\W)*("+word+")", color));
	}
	
	public final void createCustomRule(String word, int color){
		pairs.add(new WordColorPair(this.context, word, color));
	}
	
	public static class WordColorPair{
		private final String word;
		private final int color;
		private final AttributeSet attr;
		
		private WordColorPair(StyleContext context, String word, int color){
			this.word = word;
			this.color = color;
			
			this.attr = context.addAttribute(context.getEmptySet(), StyleConstants.Foreground, new Color(color));
		}
		
		public String getWord(){
			return word;
		}
		
		public AttributeSet getAttr(){
			return attr;
		}
	}
	
	public static class LangJava extends LanguageRules{ private LangJava(){
		createWordRule("private|public|protected|static|final", 0x0A0AAA);
		createWordRule("double|float|long|int|shart|char|byte|boolean|void|null", 0x8A0AAA);
		
	}}
}
