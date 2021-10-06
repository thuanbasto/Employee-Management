// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  firebase: {
    apiKey: "AIzaSyDOs_b6eCfJ6OJObUFcr612IdVtqFpitEg",
    authDomain: "fpt-chat-ffbed.firebaseapp.com",
    databaseURL: "https://fpt-chat-ffbed-default-rtdb.firebaseio.com",
    projectId: "fpt-chat-ffbed",
    storageBucket: "fpt-chat-ffbed.appspot.com",
    messagingSenderId: "241098495429",
    appId: "1:241098495429:web:c33162079ebac7b81227ac",
    measurementId: "G-KLJD3JB2F4"
  },
};

export const URL_BACKEND = "http://localhost:8081";
export const URL_IMAGE = "http://localhost:8081/static/image";
export const URL_IMAGE_DEFAULT = "assets/image/default-image.jpg";

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
