
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class EmotionsCollector{
	public static int EMOTION_VALUE;
	public static boolean DEBUG_MODE = false;
	public static void main(String[] args) {
		EMOTION_VALUE = 10; //max value for emotion = happy
		HttpHelper httpHelper = new HttpHelper(DEBUG_MODE);
		Display display = new Display();
		GridLayout gridLayout = new GridLayout();
		GridData gridData = new GridData();
		Shell shell = new Shell(display);
		Button bn;
		Button[] radios = new Button[3];
		SelectionListener selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event){
				Button bt = ((Button) event.widget);
				if(bt.getSelection()){
					if(DEBUG_MODE){
						System.out.print("[EMOTIONSCOLLECTOR-WIDGETSELECTED] : "+bt.getText());
						System.out.println(" selected = " + bt.getSelection());
					}
					if(bt.getText().equals("Happy :)")){
						EMOTION_VALUE = 10;
					}else if(bt.getText().equals("Indifferent :|")){
						EMOTION_VALUE = 5;
					}else{
						EMOTION_VALUE = 0;
					}
				}
			}
		};
		gridLayout.numColumns = 1;

		shell.setLayout(gridLayout);
		shell.setSize(350, 350);

		radios[0] = new Button(shell, SWT.RADIO);
		radios[0].setSelection(true);
		radios[0].setText("Happy :)");
		radios[0].addSelectionListener(selectionListener);
		
		radios[1] = new Button(shell, SWT.RADIO);
		radios[1].setText("Indifferent :|");
		radios[1].addSelectionListener(selectionListener);
		
		radios[2] = new Button(shell, SWT.RADIO);
		radios[2].setText("Sad :(");
		radios[2].addSelectionListener(selectionListener);

		bn = new Button(shell, SWT.PUSH);
		bn.setText("Send");
		bn.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					if(DEBUG_MODE)
						System.out.println("[EMOTIONSCOLLECTOR-HANDLEEVENT] : Bn pressed with emotion selected = "+EMOTION_VALUE);
					httpHelper.sendEmotion(EMOTION_VALUE);
					break;

				default:
					break;
				}
				
			}
		});
		
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalSpan = 10;
		gridData.widthHint = 100;
		
		bn.setLayoutData(gridData);
		radios[0].setLayoutData(gridData);
		radios[1].setLayoutData(gridData);
		radios[2].setLayoutData(gridData);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
