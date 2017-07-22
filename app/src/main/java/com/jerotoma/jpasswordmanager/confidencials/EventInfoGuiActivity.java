package com.jerotoma.jpasswordmanager.confidencials;


import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.CalenderGenerate;
import com.jerotoma.jpasswordmanager.R;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class EventInfoGuiActivity extends AccountManagerActivity implements OnClickListener {


    private Toolbar toolbar;
    private Spinner eventType;
    private ImageButton startEventButton;
    private ImageButton endEventButton;
    private ImageButton singleEventButton;
    private EditText startEvent;
    private EditText eventName;
    private EditText endEvent;
    private EditText singleEvent;
    private EditText discription;
    @SuppressWarnings("unused")
    private RadioButton morethanOneDayNo;
    @SuppressWarnings("unused")
    private RadioButton morethanOneDayYes;
    @SuppressWarnings("unused")
    private RadioButton morethanOneDayAll;
    private Button addEventButton;
    private Integer itemIndex;
    private static Boolean typeSelected = false;
    private String selectedItem;
    private static Boolean single = true;
    private static Boolean morethanOneYes = false;
    private static Boolean allDay  = false;
    private Button eventTypeButton;
    private CalenderGenerate calDate;
    private int mDay;
    private int mMonth;
    private int mYear;
    private String month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initComponent();
    }
    private void initComponent() {
        calDate           = new CalenderGenerate();
        eventName         = (EditText) findViewById(R.id.eventName);
        eventType         = (Spinner) findViewById(R.id.eventType);
        startEvent        = (EditText) findViewById(R.id.eventStart);
        endEvent          = (EditText) findViewById(R.id.eventEnd);
        singleEvent       = (EditText) findViewById(R.id.singleEvent);
        discription       = (EditText)findViewById(R.id.discription);
        startEventButton  = (ImageButton)findViewById(R.id.startEventButton);
        endEventButton    = (ImageButton)findViewById(R.id.eventEndButton);
        singleEventButton = (ImageButton)findViewById(R.id.singleEventButton);
        addEventButton    = (Button)findViewById(R.id.addEventButton);
        eventTypeButton   = (Button)findViewById(R.id.eventTypeButton);
        morethanOneDayNo  = (RadioButton)findViewById(R.id.morethanOneDayNo);
        morethanOneDayYes = (RadioButton)findViewById(R.id.morethanOneDayYes);
        morethanOneDayAll = (RadioButton)findViewById(R.id.morethanOneDayAll);




        startEvent.setOnClickListener(this);
        endEvent.setOnClickListener(this);
        startEventButton.setOnClickListener(this);
        endEventButton.setOnClickListener(this);
        singleEvent.setOnClickListener(this);
        singleEventButton.setOnClickListener(this);

        spinnerOnClickListener();
        addEventButtonsOnClickListener();

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.morethanOneDayNo:
                if (checked){
                    singleEvent.setVisibility(View.VISIBLE);
                    singleEventButton.setVisibility(View.VISIBLE);
                    startEvent.setVisibility(View.GONE);
                    endEvent.setVisibility(View.GONE);
                    startEventButton.setVisibility(View.GONE);
                    endEventButton.setVisibility(View.GONE);
                    single = true;
                    morethanOneYes =true;
                    allDay = false;
                }
                break;
            case R.id.morethanOneDayYes:
                if (checked){
                    singleEvent.setVisibility(View.GONE);
                    singleEventButton.setVisibility(View.GONE);
                    startEvent.setVisibility(View.VISIBLE);
                    endEvent.setVisibility(View.VISIBLE);
                    startEventButton.setVisibility(View.VISIBLE);
                    endEventButton.setVisibility(View.VISIBLE);
                    morethanOneYes =true;
                    allDay = false;
                    single = false;
                }
                break;
            case R.id.morethanOneDayAll:
                if (checked){
                    singleEvent.setVisibility(View.VISIBLE);
                    singleEventButton.setVisibility(View.VISIBLE);
                    startEvent.setVisibility(View.VISIBLE);
                    endEvent.setVisibility(View.VISIBLE);
                    startEventButton.setVisibility(View.VISIBLE);
                    endEventButton.setVisibility(View.VISIBLE);
                    allDay = true;
                    morethanOneYes =false;
                    single = false;
                }

                break;
        }
    }

    @Override
    public void onClick(final View v) {

        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth){

                switch(monthOfYear){
                    case  0:
                        month = "January";
                        break;
                    case  1:
                        month = "February";
                        break;
                    case  2:
                        month = "March";
                        break;
                    case  3:
                        month = "April";
                        break;
                    case  4:
                        month = "May";
                        break;
                    case  5:
                        month = "June";
                        break;
                    case 6:
                        month = "July";
                        break;
                    case  7:
                        month = "August";
                        break;
                    case  8:
                        month = "September";
                        break;
                    case 9:
                        month = "October";
                        break;
                    case  10:
                        month = "November";
                        break;
                    case  11:
                        month = "December";
                        break;
                }

                if(v == singleEvent || v == singleEventButton){

                    singleEvent.setText(month+" "+dayOfMonth+ ", " +year);

                }
                if(v == startEvent || v == startEventButton){

                    startEvent.setText(month+" "+dayOfMonth+ ", " +year);

                }
                if(v == endEvent || v == endEventButton){

                    endEvent.setText(month+" "+dayOfMonth+ ", " +year);

                }
            }
        }, mYear, mMonth, mDay);

        dpd.show();
    }
    private void addEventButtonsOnClickListener() {

        eventTypeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTypeButton.setVisibility(View.GONE);
                eventType.setVisibility(View.VISIBLE);
                eventType.performClick();
                typeSelected = true;

            }
        });

        addEventButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                long id = getPasswordManagerApplication().maxEventId() +1;
                String eventType = selectedItem;
                String eName     = eventName.getText().toString();
                String sEvent    = singleEvent.getText().toString();
                String startEv   = startEvent.getText().toString();
                String endEv     = endEvent.getText().toString();
                String eDiscr     = discription.getText().toString();

                String dateCreated  = calDate.timeElapsed();

                if(typeSelected){

                    if(single){
                        if(eName.equals("") && sEvent.equals("")){
                            Toast.makeText(EventInfoGuiActivity.this, "Please fill required field to continue", Toast.LENGTH_LONG).show();
                        }else if(eName.equals("")|| sEvent.equals("")){
                            Toast.makeText(EventInfoGuiActivity.this, "Please make sure event name and day are filled to continue", Toast.LENGTH_LONG).show();
                        }else{
                            setUserInputData(id,eventType,eName, sEvent, startEv, endEv, eDiscr, dateCreated);
                        }
                    }
                    else if(morethanOneYes){

                        if(eName.equals("")&& startEv.equals("") && endEv.equals("")){
                            Toast.makeText(EventInfoGuiActivity.this, "Please fill required field to continue", Toast.LENGTH_LONG).show();
                        }
                        else if(eName.equals("")||startEv.equals("") || endEv.equals("")){
                            Toast.makeText(EventInfoGuiActivity.this, "Please make sure event name, starting and ending day are filled to continue", Toast.LENGTH_LONG).show();
                        }
                        else {
                            setUserInputData(id,eventType,eName, sEvent, startEv, endEv, eDiscr, dateCreated);
                        }

                        }
                    }else if(allDay){
                        if(eName.equals("") && sEvent.equals("") &&

                           startEv.equals("") && endEv.equals("")){
                           Toast.makeText(EventInfoGuiActivity.this, "Please fill required field to continue", Toast.LENGTH_LONG).show();

                        }else if(eName.equals("")||startEv.equals("") || endEv.equals("")||sEvent.equals("")){
                            Toast.makeText(EventInfoGuiActivity.this, "Please make sure event name,single, starting and ending day are filled to continue", Toast.LENGTH_LONG).show();
                        }else{
                            setUserInputData(id,eventType,eName, sEvent, startEv, endEv, eDiscr, dateCreated);
                       }
                }else{

                    Toast.makeText(EventInfoGuiActivity.this, "Please select event type to continue", Toast.LENGTH_LONG).show();
                }

            }

        });

    }

    void setUserInputData(long id,String eventType,String eName, String sEvent,
                          String startEv, String endEv, String eDiscr, String dateCreated){
        if(eDiscr.equals("")) eDiscr   = "N/A";
        if(sEvent.equals("")) sEvent   = "N/A";
        if(endEv.equals(""))  endEv    = "N/A";
        if(startEv.equals("")) startEv = "N/A";

        EventInfo eInfo = new EventInfo(id,eventType, eName, sEvent, startEv, endEv, eDiscr, dateCreated);
        getPasswordManagerApplication().addEventInfo(eInfo);
        Toast.makeText(EventInfoGuiActivity.this, "You have successfully added "+eventType+" event ", Toast.LENGTH_LONG).show();
        finish();
        setTextViewToEmpty();
    }

    private void setTextViewToEmpty(){
           eventName.setText("");
           singleEvent.setText("");
           startEvent.setText("");
           endEvent.setText("");
           discription.setText("");
    }
    private void spinnerOnClickListener() {
        String[]  eType = {"Birthday","Baptism","Wedding Anniversary","Family Events","Board Meetings","Conference","Meeting","Seminar","Other"};
        ArrayAdapter<String> eventTypeAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eType);
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        eventType.setAdapter(eventTypeAdapter);
        eventType.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                itemIndex    = (Integer)parent.getSelectedItemPosition();
                selectedItem = (String)eventType.getItemAtPosition(position);

                if((itemIndex == 0)|| (itemIndex == 1)|| (itemIndex == 2) || (itemIndex == 3) ){

                    eventName.setHint("Enter individual name");
                    singleEvent.setHint("Select event day ");
                    startEvent.setHint("Starting day");
                    endEvent.setHint("Ending day");
                    discription.setHint("Description(Optional)");

                }
                else if((itemIndex == 4)||(itemIndex == 5)|| (itemIndex == 6) || (itemIndex == 7) || (itemIndex == 8)){
                    eventName.setHint("Enter event name");
                    singleEvent.setHint("Select event day ");
                    startEvent.setHint("Starting day");
                    endEvent.setHint("Ending day");
                    discription.setHint("Description(Optional)");



                }
                else if(itemIndex == 9 ){
                    eventName.setHint("Enter event name");
                    singleEvent.setHint("Select event day ");
                    startEvent.setHint("Starting day");
                    endEvent.setHint("Ending day");
                    discription.setHint("Description(Optional)");

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });

        //single = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    void log(String message){
        Log.d(getClass().getName(), message);
    }
}
