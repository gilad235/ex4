package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CalculateRootsService extends IntentService {


  public CalculateRootsService() {
    super("CalculateRootsService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent == null) return;
    long timeStartMs = System.currentTimeMillis();
    long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
    if (numberToCalculateRootsFor <= 0) {
      Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);

    }
    long i=1;
    int maxx = (int) Math.sqrt(numberToCalculateRootsFor);
    while (timeStartMs+20000>System.currentTimeMillis())
    {
      i++;
      if (numberToCalculateRootsFor%i==0)
      {
        long b=  (numberToCalculateRootsFor/i);
//        intent1.setAction("found_roots");
        Intent intent1 = new Intent("found_roots");
        intent.setAction("found_roots");
        intent1.putExtra("original_number",numberToCalculateRootsFor);
        intent1.putExtra("root1",i);
        intent1.putExtra("root2",b);
        double time = ((System.currentTimeMillis()-timeStartMs)/1000.0);
        String strtime = Double.toString(time);
        intent1.putExtra("time", strtime);
        sendBroadcast(intent1);
        return;
      }
      if (i>maxx)//prime
      {
        Intent intent1 = new Intent("found_roots");

//        intent1.setAction("found_roots");
        intent1.putExtra("original_number",numberToCalculateRootsFor);
        intent1.putExtra("root1",numberToCalculateRootsFor);
        intent1.putExtra("root2",1);
        double time = ((System.currentTimeMillis()-timeStartMs)/1000.0);
        String strtime = Double.toString(time);
        intent1.putExtra("time", (strtime));
        sendBroadcast(intent1);
        return;
      }
    }
//    intent1.setAction("stopped_calculations");
    Intent intent1 = new Intent("stopped_calculations");
    intent1.putExtra("original_number",numberToCalculateRootsFor);
    intent1.putExtra("time_until_give_up_seconds",20000);
    sendBroadcast(intent1);
    return;
    /*
    TODO:
     calculate the roots.
     check the time (using `System.currentTimeMillis()`) and stop calculations if can't find an answer after 20 seconds
     upon success (found a root, or found that the input number is prime):
      send broadcast with action "found_roots" and with extras:
       - "original_number"(long)
       - "root1"(long)
       - "root2"(long)
     upon failure (giving up after 20 seconds without an answer):
      send broadcast with action "stopped_calculations" and with extras:
       - "original_number"(long)
       - "time_until_give_up_seconds"(long) the time we tried calculating

      examples:
       for input "33", roots are (3, 11)
       for input "30", roots can be (3, 10) or (2, 15) or other options
       for input "17", roots are (17, 1)
       for input "829851628752296034247307144300617649465159", after 20 seconds give up

     */
  }
}