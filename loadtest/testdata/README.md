# Testdaten 

Die Tests in Kombination von Templates, welche den PDF-Aufbau definiert, und mit Daten gefüllt werden die für diesen Aufbau benötigt werden. 
Es werden mindestens 4 Test Scenarios definiert. 

# Test Scenario 1
``` 
{"data": [
      {
      "title": "Titel Test 1 ",
      "datumVon": "12.02.2017 16:00",
      "datumBis": "12.02.2017 17:00",
      "description": "Test ",
      "place": "Musterstrasse 12\n9000 St. Gallen\nSchweiz",
      "incharge": "Denis Bittante",
      "helper": "Lars Hauser, Someoneelse",
      "author": "Denis Bittante"
   },  
    ...
]}

```
# Test Scenario 2
```
{"group": [
      {
      "title": "21.2.12 Vortraege fuer diesen Tag",
      "entries":       [
                  {
            "time": "19:00",
            "title": "Ein Titel",
            "person": "D.Bittante",
            "isSubtitle": false,
            "subtitle": false
         },
             ...
      ],
      "footer": "Special Information as Footnote"
   },
      ...
    ]
}
```
# Test Scenario 3
```
{"data": [
      {
      "title": "Titel Test 1 ",
      "datumVon": "12.02.2017 16:00",
      "datumBis": "12.02.2017 17:00",
      "description": "Test ",
      "place": "Musterstrasse 12\n9000 St. Gallen\nSchweiz",
      "incharge": "Denis Bittante",
      "helper": "Lars Hauser, Someoneelse",
      "author": "Denis Bittante"
   },
     ...
]}

```
