
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class EmotionsCollector{
	public static void main(String[] args) {
		Display display = new Display();
		GridLayout gridLayout = new GridLayout();
		GridData gridData = new GridData();
		Shell shell = new Shell(display);
		Button bn;
		Button[] radios = new Button[3];

		gridLayout.numColumns = 1;

		shell.setLayout(gridLayout);
		shell.setSize(350, 350);

		radios[0] = new Button(shell, SWT.RADIO);
		radios[0].setSelection(true);
		radios[0].setText("Happy :)");

		radios[1] = new Button(shell, SWT.RADIO);
		radios[1].setText("Indifferent :|");
		
		radios[2] = new Button(shell, SWT.RADIO);
		radios[2].setText("Sad :(");

		bn = new Button(shell, SWT.PUSH);
		bn.setText("Send");
		
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
