module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: "http://localhost:8762",
                changeOrigin: true,
                secure: false,
                ws: true,
                pathRewrite: {
                    "^/api": "http://localhost:8762/api"
                }
            }
        }
    }
};