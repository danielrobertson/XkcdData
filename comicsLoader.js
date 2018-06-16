const axios = require("axios");
const firebase = require("firebase-admin");
const serviceAccount = require("./firebase-config.json");

// dataase config
firebase.initializeApp({
  credential: firebase.credential.cert(serviceAccount),
  databaseURL: "https://relevant-xkcd-comics.firebaseio.com"
});

const db = firebase.firestore();

// initialize database by loading comics
let start; // 1
let end; // 2008
loadComicsInRange(start, end);

/*
 * Loads comics into Firestore database from range start to end (inclusive)
 * according to the property `num` from the Xkcd API
 */
function loadComicsInRange(start, end) {
  for (let id = start; id <= end; id++) {
    const url = `https://xkcd.com/${id}/info.0.json`;
    console.log("Url - " + url);
    axios
      .get(url)
      .then(function(response) {
        const comic = response.data;
        const docRef = db.collection("comics").doc(comic.title);
        docRef.set(comic);
        console.log("success, id - " + id);
      })
      .catch(function(error) {
        console.log("error, id - " + id);
        console.log(error);
      });
  }
}
