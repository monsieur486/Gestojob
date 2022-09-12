cp env.properties.dist .env
ln -s .env env.properties
nano .env
cp prod.sh.dist prod.sh
nano prod.sh
yarn install
