#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "nodemcu-ba0ec.firebaseio.com"
#define FIREBASE_AUTH "ROYIVerXdetj0VKCnlrRCpVwgUeBRL8V1mtUUAMV"
#define WIFI_SSID "505"
#define WIFI_PASSWORD "arcticmonkeys"


int switchPin = 0;
int LedPin = 16;
int switchValue;


void setup() {
  Serial.begin(9600);

  pinMode(LedPin, OUTPUT);
  pinMode(switchPin, INPUT_PULLUP);

  
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n = 0;

void loop() {

   // Read the switch value
  switchValue = digitalRead(switchPin);
  /* Set the LED output pin the opposit of what is read on the switch
   * input pin  
   */
  digitalWrite(LedPin, !switchValue); 
    
   Serial.println(switchValue);
   //delay(1000); 
  // set value
  Firebase.setFloat("number", switchValue);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
 // delay(1000);
  
  // update value
  Firebase.setFloat("number",switchValue);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);

  // get value 
  Serial.print("number: ");
  Serial.println(Firebase.getFloat("number"));
  delay(1000);

  // remove value
  Firebase.remove("number");
  //delay(1000);

  // set string value
  Firebase.setString("message", "ARCTIC MONKEYS");
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /message failed:");
      Serial.println(Firebase.error());  
      return;
  }
//  delay(1000);
  
  // set bool value
  Firebase.setBool("truth", false);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /truth failed:");
      Serial.println(Firebase.error());  
      return;
  }
//  delay(1000);

  // append a new value to /logs
  String name = Firebase.pushInt("logs", n++);
  // handle error
  if (Firebase.failed()) {
      Serial.print("pushing /logs failed:");
      Serial.println(Firebase.error());  
      return;
  }
  Serial.print("pushed: /logs/");
  Serial.println(name);
  delay(1000);
}
