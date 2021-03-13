# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# production stage
FROM bitnami/nginx as production-stage
COPY --from=build-stage /app/dist /app
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
