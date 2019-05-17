const axios = require("axios");

let start = 404; // 1
let end = 2017; // 2008
loadComicsInRange(start, end);

/*
 * Get comics JSON from range start to end (inclusive)
 * according to the property `num` from the Xkcd API
 */
const loadComicsInRange = (start, end) => {
  for (let id = start; id <= end; id++) {
    if(id === 404) {
      continue; 
    }

    const url = `https://xkcd.com/${id}/info.0.json`;
    axios
      .get(url)
      .then((response) => {
        const comic = response.data;
        console.log(JSON.stringify(comic) + ",");
      })
      .catch(err => {
        console.log(err);
      });
  }
}
