const axios = require("axios");

let start = 404; // 1
let end = 2017; // 2008
loadComicsInRange(start, end);

/*
 * Get comics JSON from range start to end (inclusive)
 * according to the property `num` from the Xkcd API
 */
function loadComicsInRange(start, end) {
  for (let id = start; id <= end; id++) {
    const url = `https://xkcd.com/${id}/info.0.json`;
    axios
      .get(url)
      .then(function(response) {
        const comic = response.data;
        console.log(JSON.stringify(comic) + ",");
      })
      .catch(err => {
        console.log(err);
      });
  }
}
