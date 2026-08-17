#!/bin/sh
set -eu

ROOT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)

API_PID=""
if ! curl --fail --silent "http://127.0.0.1:8080/actuator/health" >/dev/null; then
  mvn -f "$ROOT_DIR/apps/api/pom.xml" spring-boot:run &
  API_PID=$!
fi
trap 'if [ -n "$API_PID" ]; then kill "$API_PID" 2>/dev/null || true; fi' EXIT INT TERM

attempt=0
until curl --fail --silent "http://127.0.0.1:8080/actuator/health" >/dev/null; do
  attempt=$((attempt + 1))
  if [ "$attempt" -ge 60 ]; then
    echo "La API no respondió en 60 segundos." >&2
    exit 1
  fi
  sleep 1
done

cd "$ROOT_DIR"
npm run dev:web
