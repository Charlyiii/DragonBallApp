# DragonBallApp

(https://github.com/Charlyiii/DragonBallApp/issues/1#issue-2286646142)

Android application that allows you to consult information about characters and planets from the Dragon Ball animated series.

On my paternity leave, in order not to lose the practice of what I learned in my first months as an Android developer, in my free time (which wasn't much) I wanted to make a simple application in which the bases of Clean Architecture were used together with the MVVM pattern, using StateFlows and applying the best architectural practices for communication between the different layers, minimizing the dependencies between them.  Dependency injection is also used with Dagger Hilt. 

I also wanted the application to make requests to a server, using Retrofit.  At first, a third-party API was used (https://web.dragonball-api.com/), but I wanted to make it my own so I could add characters and expand it in the future.  For this I used Firebase RealTime Database and Firebase Storage to store the images.

The first version released is fully functional, but it has not been exhaustively tested, unit tests are missing and some parts of the code have to be refactored, so it should be considered a Beta.  Of course, the idea is to add all this and even scale the application, adding more characters to the database and even adding new features (suggestions are accepted and appreciated).

## Contribution

If any of you can think of a character to add (many are missing), or transformations, I will be happy to add it to the database, and consequently, to the application.  To do this, you could send me a json with the character and image information, or send me the data and I'll add it.  I leave a sample of the data model of a character with transformation:
```json
{
  "id": 67,
  "name": "Janemba",
  "ki": "15 Billion",
  "maxKi": "75 Billion",
  "race": "Evil",
  "gender": "Male",
  "description": "Janemba es un demonio... ",
  "image": "https://link.to.photo",
  "affiliation": "Other",
  "deletedAt": null,
  "originPlanet": {
    "id": 18,
    "name": "Otro Mundo",
    "isDestroyed": false,
    "description": "El Otro Mundo...",
    "image": "https://link.to.planet.photo",
    "deletedAt": null
  },
  "transformations": [
    {
      "id": 42,
      "name": "Super Janemba",
      "image": "https://link.to.transfotmation.photo",
      "ki": "75 Billion",
      "deletedAt": null
    }
  ]
}
```
## Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).

## Tecnologías:
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white&labelColor=101010)]()
[![Android_Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white&labelColor=101010)]()
[![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white&labelColor=101010)]()

## License

Copyright 2024 Carlos Ferreiro

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Greetings

* [Antonio Álvarez López](https://github.com/intentodepirata)
  Thank you for letting me use your API as inspiration and guidance for this project.  Thank you for always being available for my questions

## Copyright 
All Dragon Ball characters, images, and related content featured in this project are the intellectual property of their respective creators, Akira Toriyama and Toei Animation. We acknowledge and respect their creative work. This project is a fan-created initiative and is not intended for commercial purposes. No copyright infringement is intended, and all rights to Dragon Ball belong to its rightful owners.
