# corona-stats

## Tasks

- [x] a) Fetch data for the entire world.
- [x] b) Fetch data for USA.
- [x] c) Implement pagination for USA. Use a param to specify how many records need to be fetched.
- [x] d) Bonus if this data can be stored in any data store and can be fetched on a periodic basis.

---

| API | Description |
| --- | --- |
| http://localhost:8080/all | Get All Countries Data |
| http://localhost:8080/country/USA | Get Specific Country Data |
| http://localhost:8080/country/USA?limit=2 | Get Specific Country Data With Limit |
| http://localhost:8080/refresh | Manually refresh all the countries data |

---

* Auto refresh is also configured with scheduling.
