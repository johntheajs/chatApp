# Use a minimal JRE base image (lighter & more secure than full JDK)
FROM eclipse-temurin:21-jre-alpine

# Set environment variables (non-interactive, predictable)
ENV APP_HOME=/app \
    JAVA_OPTS=""

# Create a non-root user and group with a fixed UID/GID
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Create app directory with restricted permissions
RUN mkdir -p $APP_HOME && chown appuser:appgroup $APP_HOME
WORKDIR $APP_HOME

# Copy the pre-built JAR into the image with correct ownership
COPY --chown=appuser:appgroup target/*.jar app.jar

# Restrict permissions: read & execute for owner only
RUN chmod 500 app.jar

# Switch to non-root user
USER appuser

# Expose application port
EXPOSE 8080

# Run the JAR (pass JAVA_OPTS for flexibility)
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
