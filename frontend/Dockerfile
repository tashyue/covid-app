FROM node:10

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3000

ENTRYPOINT npm start --host 0.0.0.0 --port 3000
