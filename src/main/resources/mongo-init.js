const db = db.getSiblingDB('operacoes');

db.createUser({
  user: "hiran",
  pwd: "secret",
  roles: [
    {
      role: "readWrite",
      db: "operacoes"
    }
  ]
});

db.test.insertOne({ inicializado: true });