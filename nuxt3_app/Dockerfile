# Use the official Node.js 17 image as the parent image
FROM node:17

# Set the working directory to /app
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies using pnpm
RUN npm install -g pnpm && pnpm install

# Copy all files except those specified in .dockerignore to the container
COPY . .

# Build the app for production
RUN npm run build

# Expose port 3000 for the app
EXPOSE 3000

# Start the app
CMD [ "npm", "start" ]