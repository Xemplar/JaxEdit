import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.text.*;

public class JaxEditor extends JTextPane {
	private LanguageRules rules;
    public JaxEditor(LanguageRules rules){
		this.rules = rules;
		
		final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
						boolean condMet = false;
						ArrayList<LanguageRules.WordColorPair> data = rules.getPairs();
						for(int i = 1; i < data.size(); i++){
							if (text.substring(wordL, wordR).matches(data.get(i).getWord())){
                                setCharacterAttributes(wordL, wordR - wordL, data.get(i).getAttr(), false);
                                condMet = true;
                                break;
                            }
						}
                        if(!condMet){
                            setCharacterAttributes(wordL, wordR - wordL, data.get(0).getAttr(), false);
						}
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);
                
                boolean condMet = false;
				ArrayList<LanguageRules.WordColorPair> data = rules.getPairs();
				for(int i = 1; i < data.size(); i++){
					if (text.substring(before, after).matches(data.get(i).getWord())){
						setCharacterAttributes(before, after - before, data.get(i).getAttr(), false);
                        condMet = true;
                        break;
                    }
				}
                if(!condMet){
                    setCharacterAttributes(before, after - before, data.get(0).getAttr(), false);
				}
            }
        };
        
        this.setDocument(doc);
        this.setText("public static void main() {}\nprotected int hii = 6;");
    }
    
    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
}
