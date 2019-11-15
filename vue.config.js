module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: "http://localhost:19111",
                changeOrigin: true,
                secure: false,
                ws: true,
                pathRewrite: {
                    "^/api": "http://localhost:19111/api"
                }
            }
        }
    }
};