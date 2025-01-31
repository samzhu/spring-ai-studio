#!/bin/bash

# dev-resources/install-java-formatter.sh
set -euo pipefail

# 定義版本和下載網址
VERSION="1.25.0"
JAR_URL="https://github.com/google/google-java-format/releases/download/v${VERSION}/google-java-format-${VERSION}-all-deps.jar"
MAC_URL="https://github.com/google/google-java-format/releases/download/v${VERSION}/google-java-format_darwin-arm64"
LINUX_X86_64_URL="https://github.com/google/google-java-format/releases/download/v${VERSION}/google-java-format_linux-x86-64"

echo "Installing google-java-format..."

# Check if the formatter already exists
if command -v google-java-format &> /dev/null; then
    echo "google-java-format is already installed."
    exit 0
fi

# Detect OS and architecture
OS=$(uname)
ARCH=$(uname -m)

install_jar_version() {
    echo "Installing JAR version for cross-platform compatibility..."
    
    # Download JAR file
    sudo curl -f -L -o /usr/local/lib/google-java-format.jar "${JAR_URL}" || {
        echo "Failed to download google-java-format JAR"
        exit 1
    }

    # Create wrapper script
    cat << 'EOF' | sudo tee /usr/local/bin/google-java-format > /dev/null
#!/bin/bash
java -jar "/usr/local/lib/google-java-format.jar" "$@"
EOF

    # Make executable
    sudo chmod +x /usr/local/bin/google-java-format
}

install_native_linux_x86_64() {
    echo "Installing native version for Linux x86_64..."
    
    # Create temp directory
    TMP_DIR=$(mktemp -d)
    cd "$TMP_DIR"

    # Download binary
    echo "Downloading google-java-format for Linux x86_64..."
    curl -f -L -o google-java-format "${LINUX_X86_64_URL}"

    # Make executable
    chmod +x google-java-format

    # Move to bin directory
    echo "Installing to /usr/local/bin..."
    sudo mv google-java-format /usr/local/bin/

    # Clean up
    cd - > /dev/null
    rm -rf "$TMP_DIR"
}

install_native_mac() {
    echo "Installing native version for Mac ARM64..."
    
    # Create temp directory
    TMP_DIR=$(mktemp -d)
    cd "$TMP_DIR"

    # Download binary
    echo "Downloading google-java-format for Mac ARM64..."
    curl -f -L -o google-java-format "${MAC_URL}"

    # Make executable
    chmod +x google-java-format

    # Move to bin directory
    echo "Installing to /usr/local/bin..."
    sudo mv google-java-format /usr/local/bin/

    # Clean up
    cd - > /dev/null
    rm -rf "$TMP_DIR"
}

# Install based on OS and environment
if [ "$OS" = "Darwin" ] && [ "$ARCH" = "arm64" ]; then
    # Mac ARM64
    install_native_mac
elif [ "$OS" = "Linux" ] && [ "$ARCH" = "x86_64" ]; then
    # Linux x86_64
    install_native_linux_x86_64
else
    # Default to JAR version
    echo "Unsupported architecture or OS. Using JAR version instead."
    install_jar_version
fi

# Verify installation
if command -v google-java-format &> /dev/null; then
    echo "Installation complete! Testing google-java-format..."
    google-java-format --version
    echo "You can now use 'google-java-format' command."
else
    echo "Installation failed. Please check the error messages above."
    exit 1
fi