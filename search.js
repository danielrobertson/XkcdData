const Appbase = require("appbase-js");
const appbaseRef = new Appbase({
  url: "https://scalr.api.appbase.io",
  app: "relevant-xkcd",
  credentials: process.env.appbase
});

appbaseRef
  .search({
    type: "comics",
    body: {
      query: {
        query_string: {
          query: "ocean"
        }
      }
    }
  })
  .on("data", function(res) {
    console.log("query result: ", JSON.stringify(res, null, 4));
  })
  .on("error", function(err) {
    console.log("search error: ", err);
  });
