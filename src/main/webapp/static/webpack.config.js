var path = require("path");
module.exports = {
    entry: "./js/index.js",
    output: {
      path: path.join(__dirname, "dist"),
      filename: "build.js"
    },
    devtool: "source-map",
    module: {
      rules: [
        {
          test: /\.vue$/,
          loader: 'vue-loader',
          options: {
            loaders: {
              js: 'babel-loader!eslint-loader'
            }
          }
        }
      ],
      loaders: [
        {
          test: /\.js$/,
          loader: "babel-loader",
          exclude: /node_modules/
        },
        {
          test: /\.vue$/,
          loader: 'vue-loader'
        }
      ]
    },
    resolve: {
      alias: {
        'vue$': 'vue/dist/vue.esm.js'
      }
    }
}